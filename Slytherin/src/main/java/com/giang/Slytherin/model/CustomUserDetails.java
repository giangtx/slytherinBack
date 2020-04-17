package com.giang.Slytherin.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    TaiKhoan taikhoan;

    public CustomUserDetails(TaiKhoan taiKhoan,Collection<? extends GrantedAuthority> author){
        this.taikhoan=taiKhoan;
        this.authorities=author;
    }
    public CustomUserDetails(TaiKhoan taiKhoan){
        this.taikhoan=taiKhoan;
    }
    //ROLE_ADMIN và ROLE_USER
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return taikhoan.getMatKhau();
    }

    @Override
    public String getUsername() {
        return taikhoan.getTenDangNhap();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
