package com.dotnar.usc.web;

import com.dotnar.usc.core.shiro.SysSecurityUtils;
import com.dotnar.usc.model.MyUserDetails;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

//    @RequestMapping("/user")
//    public Principal user(Principal principal) {
//        return principal;
//    }

    @RequestMapping("/me")
    public Map<String,Object> user(Principal principal) {
        Map<String, Object> retMap = new HashedMap();//Collections.emptyMap();
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
        retMap.put("name",principal.getName());
        retMap.put("mobile",userDetails.getMobile());
        retMap.put("nickname",userDetails.getNickname());
        retMap.put("nationCode",userDetails.getNationCode());
        retMap.put("uuid",userDetails.getUuid());

        return retMap;
    }


    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test() {
        Map<String, Object> retMap = new HashedMap();//Collections.emptyMap();
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        retMap.put("authentication=",authentication);
        if(authentication!=null){
            if(authentication instanceof MyUserDetails){
                MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
                retMap.put("mobile",userDetails.getMobile());
                retMap.put("nickname",userDetails.getNickname());
                retMap.put("nationCode",userDetails.getNationCode());
            }
        }

        return retMap;
    }
      
}  