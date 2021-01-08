package com.stock_management.mapper;

import com.stock_management.dto.CustomerDto;
import com.stock_management.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-08T16:49:27+0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto mapCustomerEntityToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerId( customer.getCustomerId() );
        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );
        customerDto.setAddress( customer.getAddress() );
        customerDto.setTelephoneNumber( customer.getTelephoneNumber() );

        return customerDto;
    }

    @Override
    public Customer mapCustomerDtoToEntity(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerId( customerDto.getCustomerId() );
        customer.setFirstName( customerDto.getFirstName() );
        customer.setLastName( customerDto.getLastName() );
        customer.setAddress( customerDto.getAddress() );
        customer.setTelephoneNumber( customerDto.getTelephoneNumber() );

        return customer;
    }
}
