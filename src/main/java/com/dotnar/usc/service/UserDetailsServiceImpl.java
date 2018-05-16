package com.dotnar.usc.service;

import com.dotnar.usc.core.model.UsUser;
import com.dotnar.usc.core.service.IUsUserService;
import com.dotnar.usc.model.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

/**
 * @author longshengtang
 * @create 2018-02-28 20:05
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            logger.error("username 不能为空");
            throw new UsernameNotFoundException("用户不存在");
        }
        UsUser para = new UsUser();
        para.setUsername(username);
        UsUser dbUser = userService.selectOne(para);
        if (dbUser == null) {
            return null;
        }

        MyUserDetails userDetails = new MyUserDetails(dbUser);
        BeanUtils.copyProperties(dbUser, userDetails);//
        return userDetails;
    }

    @Autowired
    private IUsUserService userService;//是否还有事务？
}
