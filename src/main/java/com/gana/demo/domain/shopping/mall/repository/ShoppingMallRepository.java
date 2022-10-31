package com.gana.demo.domain.shopping.mall.repository;

import com.gana.demo.domain.shopping.mall.ShoppingMall;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallFormData;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallItem;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallNavItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingMallRepository extends JpaRepository<ShoppingMall, Long> {
    List<ShoppingMallNavItem> findAllNavItemBy();

    List<ShoppingMallItem> findAllItemBy();

    Optional<ShoppingMallFormData> findFormDataById(Long id);
}
