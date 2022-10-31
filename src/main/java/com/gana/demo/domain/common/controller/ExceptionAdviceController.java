package com.gana.demo.domain.common.controller;

import com.gana.demo.domain.shopping.mall.controller.ShoppingMallController;
import com.gana.demo.domain.member.controller.MemberController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice(
        assignableTypes = {
                BaseController.class,
                ExceptionAdviceController.class,
                MemberController.class,
                ShoppingMallController.class,
        }
)
@Controller
public class ExceptionAdviceController {
    @ExceptionHandler({
            NoHandlerFoundException.class,
            MethodArgumentTypeMismatchException.class,
            RequestRejectedException.class
    })
    public String redirectIndex() {
        return "redirect:/";
    }

    @ExceptionHandler({
            RuntimeException.class,
            Exception.class
    })
    public String internalServerError(Exception e) {
        log.error(e.getMessage());

        return "error/500";
    }

    @ExceptionHandler({
            DataAccessException.class
    })
    public String serviceUnavailable(Exception e) {
        log.error(e.getMessage());

        return "error/503";
    }
}