package com.gana.demo.domain.common.controller;

import com.gana.demo.domain.shopping.mall.controller.ShoppingMallController;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallNavItem;
import com.gana.demo.domain.shopping.mall.service.ShoppingMallService;
import com.gana.demo.domain.member.controller.MemberController;
import com.gana.demo.domain.member.dto.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice(
        assignableTypes = {
                BaseController.class,
                ExceptionAdviceController.class,
                MemberController.class,
                ShoppingMallController.class,
        }
)
@Controller
public class ModelAttributeAdviceController {
    private final ShoppingMallService shoppingMallService;

    @ModelAttribute
    public void bindMember(
            @AuthenticationPrincipal MemberDetails member,
            Model model
    ) {
        if (member != null) {
            model.addAttribute("member", member);
        }
    }

    @ModelAttribute
    public void bindShoppingMallNavItems(
            Model model
    ) {
        List<ShoppingMallNavItem> shoppingNavItems = shoppingMallService.getNavItems();
        model.addAttribute("shoppingNavItems", shoppingNavItems);
    }

    @GetMapping("/fragment")
    public String fragment() {
        return "fragment";
    }
}
