package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.request.SigninRequest;
import com.giang.Slytherin.controller.response.TaiKhoanData;
import com.giang.Slytherin.model.ChucVu;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.model.TaiKhoan;
import com.giang.Slytherin.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public TaiKhoan getUserById(int id){
        return taiKhoanRepository.findById(id);
    }

    public TaiKhoanData getInfoTest(){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaiKhoanData data=new TaiKhoanData();
        data.setMataikhoan(customUserDetails.getTaikhoan().getMaTaiKhoan());
        data.setTendangnhap(customUserDetails.getUsername());
        data.setAnhdaidien(customUserDetails.getTaikhoan().getAnhDaiDien());
        data.setEmail(customUserDetails.getTaikhoan().getEmail());
        return data;
    }
    public Boolean xuLyDangKy(SigninRequest request){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        TaiKhoan taiKhoankt=taiKhoanRepository.findByTenDangNhap(request.getUsername());
        if(taiKhoankt==null){
            TaiKhoan taikhoan=new TaiKhoan();
            taikhoan.setTenDangNhap(request.getUsername());
            taikhoan.setMatKhau(hashedPassword);
            taikhoan.setEmail(request.getEmail());
            taikhoan.setAnhDaiDien("user.png");
            ChucVu chucvu= new ChucVu();
            chucvu.setMaChucVu(2);
            taikhoan.setChucvu(chucvu);
            taikhoan.setVerification(getAlphaNumericString(8));
            taiKhoanRepository.save(taikhoan);
            return true;
        }else{
            return false;
        }
    }
    public String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}
