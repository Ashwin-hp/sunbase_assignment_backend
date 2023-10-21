package com.ashkash.sunbase_backend.mapper;

import com.ashkash.sunbase_backend.dto.CustomerDTO;
import com.ashkash.sunbase_backend.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO CustomerToCustomerDTO(Customer customer);
}
