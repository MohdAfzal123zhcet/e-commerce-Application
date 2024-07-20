package com.example.e_commerce.Service.ServiceInterfaceImplementation;

import com.example.e_commerce.Dto.ReqDto.SellerReqDto;
import com.example.e_commerce.Dto.RespDto.SellerRespDto;
import com.example.e_commerce.Exception.SellerException;
import com.example.e_commerce.Repository.SellerRepository;
import com.example.e_commerce.Service.ServiceInterface.SellerServiceInterface;
import com.example.e_commerce.convertor.SellerConvertor;
import com.example.e_commerce.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SellerServiceImplementation implements SellerServiceInterface {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerRespDto addSeller(SellerReqDto sellerReqDto) throws SellerException
    {
        Seller seller;
      try {
          seller = SellerConvertor.SellerReqDtoToSeller(sellerReqDto);
          sellerRepository.save(seller);
        }
      catch(Exception e) {
          throw new SellerException("email already registered");
      }
        SellerRespDto  sellerRespDto=SellerConvertor.SellerToSellerRespDto(seller);
            return sellerRespDto;

    }

}
