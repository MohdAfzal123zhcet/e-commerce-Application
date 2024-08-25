package com.example.e_commerce.Service.ServiceInterfaceImplementation;
import com.example.e_commerce.Dto.ReqDto.CheckoutCartRequestDto;
import com.example.e_commerce.Dto.RespDto.CartResponseDto;
import com.example.e_commerce.Dto.RespDto.ItemResponseDto;
import com.example.e_commerce.Dto.RespDto.OrderResponseDto;
import com.example.e_commerce.Exception.InvalidCardException;
import com.example.e_commerce.Exception.CustomerException;
import com.example.e_commerce.model.*;
        import com.example.e_commerce.Repository.CardRepository;
import com.example.e_commerce.Repository.CartRepository;
import com.example.e_commerce.Repository.CustomerRepository;
import com.example.e_commerce.Repository.OrderRepository;
import com.example.e_commerce.convertor.ItemConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderedRepository;

    @Autowired
    CardRepository cardRepository;

    public CartResponseDto saveCart(Integer customerId, Item item){

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal() + item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItemList().add(item);
        cart.setNumberOfItems(cart.getItemList().size());
        item.setCart(cart);
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItems(savedCart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item itemEntity: savedCart.getItemList()){
            ItemResponseDto itemResponseDto = ItemConvertor.ItemToItemResponseDto(itemEntity);
            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerException("Customer id is invalid!!!");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is empty!!");
        }

        try{
            Order order = orderService.placeOrder(customer,card);  // throw exception if product goes out of stock
            customer.getOrderList().add(order);
            resetCart(cart);
            Order savedOrder = orderedRepository.save(order);

            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate((Date) savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItemList()){
                ItemResponseDto itemResponseDto = ItemConvertor.ItemToItemResponseDto(itemEntity);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItemList()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItemList().clear();

    }
}
