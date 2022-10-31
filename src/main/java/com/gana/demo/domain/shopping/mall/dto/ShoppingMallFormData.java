package com.gana.demo.domain.shopping.mall.dto;

import java.util.List;

public interface ShoppingMallFormData {
    String getName();
    List<String> getThumbnails();
    String getAddress();
    String getPhone();
    String getOpenDate();
    String getCloseDate();
    String getParking() ;
    String getTransport();
    String getFacility();
    String getMap();
    String getInfo();
}
