package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.jwt.JwtTokenProvider;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.payload.LoginRequest;
import com.giang.Slytherin.payload.LoginResponse;
import com.giang.Slytherin.service.BinhLuanServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v2")
public class TestController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken(authentication);
        return new LoginResponse(jwt);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public String randomStuff(){
        return "JWT Hợp lệ mới có thể thấy được message này";
    }
    @Autowired
    HinhAnhServiceImp hinhAnhServiceImp;


    @GetMapping("/hinhanh/{id}")
    public HinhAnhData findHinhAnhById(@PathVariable("id") long id){
        return hinhAnhServiceImp.findByMaHinhAnh(id,0);
    }

    @GetMapping("/admin")
    public String adminTest(){
        return "admin hi";
    }
    @GetMapping("/user")
    public String userTest(){
        return "user";
    }
    @GetMapping("/useradmin")
    public String userAminTest(){
        return "user và admin";
    }
}
