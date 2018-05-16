package com.dotnar.usc.model;

import com.dotnar.usc.core.model.UsUser;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author longshengtang
 * @create 2018-02-28 20:06
 **/

public class MyUserDetails extends User implements UserDetails {

    /**
     * 手机,所属表字段为us_user.mobile
     * @flysky_generated
     */
    @ApiModelProperty("手机")
    private String mobile;

    /**
     * 用户昵称,所属表字段为us_user.nickname
     * @flysky_generated
     */
    @ApiModelProperty("用户昵称")
    private String nickname;


    /**
     * 国家代码,所属表字段为us_user.nation_code
     * @flysky_generated
     */
    @ApiModelProperty("国家代码")
    private String nationCode;
    /**
     * 国家名称,所属表字段为us_user.nation_name
     * @flysky_generated
     */
    @ApiModelProperty("国家名称")
    private String nationName;

    /**
     * 用户唯一标识uuid
     * @flysky_generated
     */
    @ApiModelProperty("用户唯一标识uuid")
    private String uuid;


    /**
     * 用户标志(1为正常用户，2为内部用户),所属表字段为us_user.flag
     * @flysky_generated
     */
    @ApiModelProperty("用户标志(1为正常用户，2为内部用户)")
    private String flag;

    public MyUserDetails(UsUser user) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, buildUserAuthorities(user));
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 把用户的权限 UserRole 转换成 GrantedAuthority
     *
     * @param user 用户
     * @return
     */
    private static List<GrantedAuthority> buildUserAuthorities(UsUser user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // Build user's authorities
        authorities.add(new SimpleGrantedAuthority("USER"));//现在写死
        return new ArrayList<>(authorities);
    }

    private static class UserRole {
        public String getRole() {
            return "USER";
        }
    }
}
