package com.dotnar.usc.provider;

import com.dotnar.usc.common.base.BaseResp;
import com.dotnar.usc.common.em.PlatformEm;
import com.dotnar.usc.common.em.StatusEm;
import com.dotnar.usc.core.service.ITotpService;
import com.dotnar.usc.core.shiro.exception.StatusLockedException;
import com.dotnar.usc.core.vo.totp.VerifyPasscodeByUsernameVo;
import com.dotnar.usc.core.vo.user.UserSystemStatus;
import com.dotnar.usc.model.MyUserDetails;
import com.dotnar.usc.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ITotpService totpService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // [1] token 中的用户名和密码都是用户输入的，不是数据库里的
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // [2] 使用用户名从数据库读取用户信息
        MyUserDetails userDetails = userDetailsService.loadUserByUsername(token.getName());
        // [3] 检查用户信息
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        VerifyPasscodeByUsernameVo vo = new VerifyPasscodeByUsernameVo();
        vo.setUsername(userDetails.getUsername());
        vo.setReqSysId(PlatformEm.USER_SAFE_CENTER.getPlatformId());
        vo.setVerificationType("1");
        BaseResp<UserSystemStatus> userSystemStatusBaseResp = totpService.checkUserStatus(vo);
        if (!userSystemStatusBaseResp.isOk()) {
            if (StatusEm.USER_TEMP_LOCK.getCode().equals(userSystemStatusBaseResp.getCode())) {
                throw new LockedException("用户被临时锁定");
            } else if (StatusEm.USER_LOCK_BY_ADMIN.getCode().equals(userSystemStatusBaseResp.getCode())) {
                throw new StatusLockedException("用户被管理员锁定");
            } else if (StatusEm.USER_LOCK_BY_PASSWORD_ERROR.getCode().equals(userSystemStatusBaseResp.getCode())) {
                throw new LockedException("用户状态永久锁定");
            } else if (StatusEm.USER_STATUS_INVALID.getCode().equals(userSystemStatusBaseResp.getCode())) {
                throw new DisabledException("无效用户状态");
            } else if (StatusEm.PLATFORM_NOT_AUTHORIZED.getCode().equals(userSystemStatusBaseResp.getCode())) {
                throw new DisabledException("未授权系统");
            } else if (StatusEm.SYSTEM_BOUND_STATUS_FROZEN.getCode().equals(userSystemStatusBaseResp.getCode())) {
                throw new DisabledException("系统被冻结");
            } else if (StatusEm.SYSTEM_BOUND_STATUS_UNPROTECTED.getCode().equals(userSystemStatusBaseResp.getCode())) {
                throw new DisabledException("未开启系统保护");
            } else {
                throw new AuthenticationServiceException("状态检测异常");
            }
        }
        String encryptedPassword = userDetails.getPassword();   // 数据库用户的密码，一般都是加密过的
        String inputPassword = (String) token.getCredentials(); // 用户输入的密码
        // 根据加密算法加密用户输入的密码，然后和数据库中保存的密码进行比较
        if (!passwordEncoder.matches(inputPassword, encryptedPassword)) {
            throw new BadCredentialsException("用户名/密码无效");
        }
//        }
        // [5] 成功登陆，把用户信息提交给 Spring Security
        // 把 userDetails 作为 principal 的好处是可以放自定义的 UserDetails，这样可以存储更多有用的信息，而不只是 username，
        // 默认只有 username，这里的密码使用数据库中保存的密码，而不是用户输入的明文密码，否则就暴露了密码的明文
        return new UsernamePasswordAuthenticationToken(userDetails, "auth_credentials_test", userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}