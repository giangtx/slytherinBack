package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.TaiKhoanData;
import com.giang.Slytherin.model.ChucVu;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.model.TaiKhoan;
import com.giang.Slytherin.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaiKhoanServiceImp implements UserDetailsService {

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    public TaiKhoan findByTenDangNhap(String username){
        return taiKhoanRepository.findByTenDangNhap(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan=taiKhoanRepository.findByTenDangNhap(username);
        if(taiKhoan==null){
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<ChucVu> cv=new ArrayList<>();
        cv.add(taiKhoan.getChucvu());
        for (ChucVu role : cv){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getTenChucVu()));
        }
        return new CustomUserDetails(taiKhoan,grantedAuthorities);
    }
    @Transactional
    public UserDetails loadUserById(Long id) {
        TaiKhoan user = taiKhoanRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<ChucVu> cv=new ArrayList<>();
        cv.add(user.getChucvu());
        for (ChucVu role : cv){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getTenChucVu()));
        }
        return new CustomUserDetails(user,grantedAuthorities);
    }

}
