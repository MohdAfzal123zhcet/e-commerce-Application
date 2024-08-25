package com.example.e_commerce.Service.ServiceInterfaceImplementation;

import com.example.e_commerce.Enum.ProductCategory;
import com.example.e_commerce.Enum.ProductStatus;
import com.example.e_commerce.Dto.ReqDto.ProductRequestDto;
import com.example.e_commerce.Dto.RespDto.ProductResponseDto;
import com.example.e_commerce.Exception.SellerException;
import com.example.e_commerce.model.Item;
import com.example.e_commerce.model.Order;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.model.Seller;
import com.example.e_commerce.Repository.ProductRepository;
import com.example.e_commerce.Repository.SellerRepository;
import com.example.e_commerce.convertor.ProductConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerException {

        Seller seller;
        try{
            seller =  sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new SellerException("Seller doesn't exist");
        }

        Product product = ProductConvertor.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        // add product to current products of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);  // saves both seller and product

        // prepare Response Dto
        return ProductConvertor.ProductToProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category){

        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductConvertor.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category){

        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price,category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductConvertor.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

    public void decreaseProductQuantity(Item item) throws Exception {

        Product product = item.getProduct();
        int quantity = item.getRequiredQuantity();
        int currentQuantity = product.getQuantity();
        if(quantity>currentQuantity){
            throw new Exception("Out of stock");
        }
        product.setQuantity(currentQuantity-quantity);
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }
}