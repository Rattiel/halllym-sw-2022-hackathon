package com.gana.demo.domain.shopping.mall.service;

import com.gana.demo.domain.shopping.mall.ShoppingMall;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallFormData;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallItem;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallNavItem;
import com.gana.demo.domain.shopping.mall.exception.ShoppingMallNameExistsException;
import com.gana.demo.domain.shopping.mall.exception.ShoppingMallNotFoundException;
import com.gana.demo.domain.shopping.mall.repository.ShoppingMallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DefaultShoppingMallService implements ShoppingMallService {
    private final ShoppingMallRepository shoppingMallRepository;

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ShoppingMall create(
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
    ) throws ShoppingMallNotFoundException, ShoppingMallNameExistsException {
        ShoppingMall shoppingMall = ShoppingMall.create(
                name,
                thumbnails,
                address,
                phone,
                openDate,
                closeDate,
                parking,
                transport,
                facility,
                map,
                info
        );

        return shoppingMallRepository.save(shoppingMall);
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ShoppingMall update(
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
    ) throws ShoppingMallNotFoundException, ShoppingMallNameExistsException {
        ShoppingMall shoppingMall = get(id);

        return shoppingMall.update(
                name,
                thumbnails,
                address,
                phone,
                openDate,
                closeDate,
                parking,
                transport,
                facility,
                map,
                info
        );
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(long id) throws ShoppingMallNotFoundException {
        ShoppingMall shoppingMall = get(id);

        shoppingMallRepository.delete(shoppingMall);
    }

    @Transactional
    @Override
    public ShoppingMall get(long id) throws ShoppingMallNotFoundException {
        return shoppingMallRepository.findById(id)
                .orElseThrow(ShoppingMallNotFoundException::new);
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ShoppingMallFormData getFormData(long id) throws ShoppingMallNotFoundException {
        return shoppingMallRepository.findFormDataById(id)
                .orElseThrow(ShoppingMallNotFoundException::new);
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ShoppingMallItem> getItems() {
        return shoppingMallRepository.findAllItemBy();
    }

    @Transactional
    @Override
    public List<ShoppingMallNavItem> getNavItems() {
        return shoppingMallRepository.findAllNavItemBy();
    }
}
