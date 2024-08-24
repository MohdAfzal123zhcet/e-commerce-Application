
package com.example.e_commerce.Service.ServiceInterfaceImplementation;

import com.example.e_commerce.Enum.ProductStatus;
import com.example.e_commerce.Dto.ReqDto.ItemRequestDto;
import com.example.e_commerce.Exception.CustomerException;
import com.example.e_commerce.Exception.ProductException;
import com.example.e_commerce.model.Customer;
import com.example.e_commerce.model.Item;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.Repository.CustomerRepository;
import com.example.e_commerce.Repository.ItemRepository;
import com.example.e_commerce.Repository.ProductRepository;
import com.example.e_commerce.convertor.ItemConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductException("Product doesn't exist");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product out of Stock");
        }

        Item item = ItemConvertor.ItemRequestDtoToItem(itemRequestDto);
        // item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);
        return itemRepository.save(item);
    }
}
