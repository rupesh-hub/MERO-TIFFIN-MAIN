package com.merotifiin.address.convertor;

import com.merotifiin.address.entity.Address;
import com.merotifiin.address.model.AddressPojo;

public class AddressConvertor {

    public static Address toEntity(final AddressPojo source){
        final Address address = new Address();

        address.setCountry(source.getCountry());
        address.setZone(source.getZone());
        address.setCity(source.getCity());
        address.setStreet(source.getStreet());
        address.setCreatedBy(source.getCreatedBy());
        address.setCreatedDate(source.getCreatedDate());
        address.setModifiedBy(source.getModifiedBy());
        address.setModifiedDate(source.getModifiedDate());
        address.setIsActive(source.getIsActive());

        return address;
    }

    public static AddressPojo toPojo(final Address source){
        final AddressPojo addressPojo = new AddressPojo();

        addressPojo.setCountry(source.getCountry());
        addressPojo.setZone(source.getZone());
        addressPojo.setCity(source.getCity());
        addressPojo.setStreet(source.getStreet());
        addressPojo.setCreatedBy(source.getCreatedBy());
        addressPojo.setCreatedDate(source.getCreatedDate());
        addressPojo.setModifiedBy(source.getModifiedBy());
        addressPojo.setModifiedDate(source.getModifiedDate());
        addressPojo.setIsActive(source.getIsActive());

        return addressPojo;
    }

}
