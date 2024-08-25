package com.example.e_commerce.Service.ServiceInterfaceImplementation;

import com.example.e_commerce.Enum.ProductStatus;
import com.example.e_commerce.Dto.ReqDto.OrderRequestDto;
import com.example.e_commerce.Dto.RespDto.ItemResponseDto;
import com.example.e_commerce.Dto.RespDto.OrderResponseDto;
import com.example.e_commerce.Exception.InvalidCardException;
import com.example.e_commerce.Exception.CustomerException;
import com.example.e_commerce.Exception.ProductException;
import com.example.e_commerce.model.*;
        import com.example.e_commerce.Repository.CardRepository;
import com.example.e_commerce.Repository.CustomerRepository;
import com.example.e_commerce.Repository.OrderRepository;
import com.example.e_commerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRespository;
    @Autowired
    private OrderRepository orderedRepository;

    public Order placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Order order = new Order();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItemList()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new Exception("Product Out of stock");
            }
        }
        order.setItemList(orderedItems);
        for(Item item: orderedItems)
            item.setOrder(order);
        order.setTotalValue(cart.getCartTotal());
        return order;
    }

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductException("Product doesn't exist");
        }

        Card card = cardRespository.findByCardNo(orderRequestDto.getCardNo());
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Order order = new Order();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItemList().add(item);

        customer.getOrderList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Order savedOrder = orderedRepository.save(order); // order and item

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate((Date) savedOrder.getOrderDate());
        orderResponseDto.setCardUsed(savedOrder.getCardUsed());
        orderResponseDto.setCustomerName(customer.getName());
        orderResponseDto.setOrderNo(savedOrder.getOrderNo());
        orderResponseDto.setTotalValue(savedOrder.getTotalValue());

        List<ItemResponseDto> items = new ArrayList<>();
        for(Item itemEntity: savedOrder.getItemList()){
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            items.add(itemResponseDto);
        }

        orderResponseDto.setItems(items);
        return orderResponseDto;

    }

    public String generateMaskedCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0;i<cardNo.length()-4;i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;

    }
}