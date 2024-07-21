package com.example.e_commerce.Service.ServiceInterfaceImplementation;

import com.example.e_commerce.Dto.ReqDto.CardReqDto;
import com.example.e_commerce.Dto.RespDto.CardRespDto;
import com.example.e_commerce.Exception.CustomerException;
import com.example.e_commerce.Repository.CardRepository;
import com.example.e_commerce.Repository.CustomerRepository;
import com.example.e_commerce.Service.ServiceInterface.CardServiceInterface;
import com.example.e_commerce.convertor.CardConvertor;
import com.example.e_commerce.model.Card;
import com.example.e_commerce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplementation implements CardServiceInterface {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    public CardRespDto addCard(CardReqDto cardReqDto)throws CustomerException
    {
        Customer customer;

            customer = customerRepository.findByMob(cardReqDto.getMob());
            if(customer==null)
                throw new CustomerException("Sorry!Customer not exist");

            Card card=CardConvertor.CardReqDtoToCard(cardReqDto);
            card.setCustomer(customer);
            cardRepository.save(card);
            return CardRespDto.builder()
                .name(customer.getName())
                .cardNo(cardReqDto.getCardNo())
                .build();

    }
}
