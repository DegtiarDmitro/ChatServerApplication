package com.chatsystem.server.ui;


import com.chatsystem.server.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class SiteController {



    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public ModelAndView actionIndex() {
        ModelAndView modelAndView = new ModelAndView("login");
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
            modelAndView.setViewName("index");
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }


}
