package com.dotnar.usc.web;

import com.dotnar.usc.config.SimpleAuthenticationFilter;
import org.apache.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)//配合theme-leaf可以
    public String login(HttpServletRequest servletRequest){
        /*ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login?lang=en");*/
        /*return modelAndView;*/
        return "redirect:/login.html?lang=en";
    }



    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("userName", "Welcome " + principal.getName());
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}