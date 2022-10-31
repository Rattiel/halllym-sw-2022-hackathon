package com.gana.demo.domain.shopping.mall.service;

import com.gana.demo.domain.shopping.mall.ShoppingMall;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallFormData;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallItem;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallNavItem;
import com.gana.demo.domain.shopping.mall.exception.ShoppingMallNameExistsException;
import com.gana.demo.domain.shopping.mall.exception.ShoppingMallNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

public interface ShoppingMallService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ShoppingMall create(
            String name,
            List<String> thumbnails,
            String address,
            String phone,
            String openDate,
            String closeDate,
            String parking,
            String transport,
            String facility,
            String map,
            String info
    ) throws ShoppingMallNotFoundException, ShoppingMallNameExistsException;

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ShoppingMall update(
            long id,
            String name,
            List<String> thumbnails,
            String address,
            String phone,
            String openDate,
            String closeDate,
            String parking,
            String transport,
            String facility,
            String map,
            String info
    ) throws ShoppingMallNotFoundException, ShoppingMallNameExistsException;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(long id) throws ShoppingMallNotFoundException;

    ShoppingMall get(long id) throws ShoppingMallNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<ShoppingMallItem> getItems();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ShoppingMallFormData getFormData(long id) throws ShoppingMallNotFoundException;

    List<ShoppingMallNavItem> getNavItems();
}
