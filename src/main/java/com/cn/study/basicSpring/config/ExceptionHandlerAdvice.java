package com.cn.study.basicSpring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 配置control类的全局属性
 * Created by Administrator on 2017/6/26.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute
    private void addAttributes(Model model) {
        model.addAttribute("msg", "你访问的页面出错");
    }

    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }


}
