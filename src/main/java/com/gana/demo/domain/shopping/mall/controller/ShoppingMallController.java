package com.gana.demo.domain.shopping.mall.controller;

import com.gana.demo.domain.common.exception.FieldException;
import com.gana.demo.domain.shopping.mall.ShoppingMall;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallForm;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallFormData;
import com.gana.demo.domain.shopping.mall.dto.ShoppingMallItem;
import com.gana.demo.domain.shopping.mall.exception.ShoppingMallNameExistsException;
import com.gana.demo.domain.shopping.mall.exception.ShoppingMallNotFoundException;
import com.gana.demo.domain.shopping.mall.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/shopping/mall")
@Controller
public class ShoppingMallController {
    private final ShoppingMallService shoppingMallService;

    @ExceptionHandler({ShoppingMallNotFoundException.class})
    public String redirectIndex() {
        return "redirect:/shopping/mall/manage";
    }

    @GetMapping("/manage")
    public String renderManagePage(Model model) {
        List<ShoppingMallItem> shoppingMallItems = shoppingMallService.getItems();
        model.addAttribute("shoppingMallItems", shoppingMallItems);

        return "/shopping/mall/manage";
    }

    @GetMapping("/{id}")
    public String renderViewPage(
            @PathVariable Long id,
            Model model
    ) {
        ShoppingMall shoppingMall = shoppingMallService.get(id);
        model.addAttribute("shoppingMall", shoppingMall);

        return "/shopping/mall/view";
    }

    @GetMapping("/new/create")
    public String renderCreatePage(Model model) {
        ShoppingMallForm form = new ShoppingMallForm();
        model.addAttribute("form", form);

        return "/shopping/mall/create";
    }

    @PostMapping("/new/create")
    public String requestCreate(
            @Valid @ModelAttribute("form") ShoppingMallForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/shopping/mall/create";
        }

        try {
            ShoppingMall shoppingMall = shoppingMallService.create(
                    form.getName(),
                    form.getThumbnails(),
                    form.getAddress(),
                    form.getPhone(),
                    form.getOpenDate(),
                    form.getCloseDate(),
                    form.getParking(),
                    form.getTransport(),
                    form.getFacility(),
                    form.getMap(),
                    form.getInfo()
            );

            return String.format("redirect:/shopping/mall/%d", shoppingMall.getId());
        } catch (ShoppingMallNameExistsException e) {
            bindFieldError(bindingResult, e);
            return "/shopping/mall/create";
        }
    }

    @GetMapping("/{id}/update")
    public String renderUpdatePage(
            @PathVariable Long id,
            Model model
    ) {
        ShoppingMallFormData data = shoppingMallService.getFormData(id);
        ShoppingMallForm form = ShoppingMallForm.of(data);
        model.addAttribute("form", form);

        return "/shopping/mall/update";
    }

    @PostMapping("/{id}/update")
    public String requestUpdate(
            @PathVariable Long id,
            @Valid @ModelAttribute("form") ShoppingMallForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/shopping/mall/update";
        }

        try {
            ShoppingMall shoppingMall = shoppingMallService.update(
                    id,
                    form.getName(),
                    form.getThumbnails(),
                    form.getAddress(),
                    form.getPhone(),
                    form.getOpenDate(),
                    form.getCloseDate(),
                    form.getParking(),
                    form.getTransport(),
                    form.getFacility(),
                    form.getMap(),
                    form.getInfo()
            );

            return String.format("redirect:/shopping/mall/%d", shoppingMall.getId());
        } catch (ShoppingMallNameExistsException e) {
            bindFieldError(bindingResult, e);
            return "/shopping/mall/update";
        }
    }

    @PostMapping("/{id}/delete")
    public String requestDelete(
            @PathVariable Long id
    ) {
            shoppingMallService.delete(id);
            return "redirect:/shopping/mall/manage";
    }

    private void bindFieldError(BindingResult bindingResult, FieldException e) {
        FieldError fieldError = new FieldError("form", e.getField(), e.getReason());
        bindingResult.addError(fieldError);
    }
}