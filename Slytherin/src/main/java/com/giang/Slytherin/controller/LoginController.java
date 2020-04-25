package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.request.LoginRequest;
import com.giang.Slytherin.controller.request.SigninRequest;
import com.giang.Slytherin.controller.response.LoginResponse;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import com.giang.Slytherin.jwt.JwtTokenProvider;
import com.giang.Slytherin.service.TaiKhoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private TaiKhoanServiceImp taiKhoanServiceImp;

    @PostMapping("/public/login")
    public ResponseEntity<BaseResponse<LoginResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
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
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").with(new LoginResponse(jwt)).build());
    }
    @PostMapping("/public/signin")
    public ResponseEntity<BaseResponse> xuLyDangKy(@Valid @RequestBody SigninRequest request){
        if(taiKhoanServiceImp.xuLyDangKy(request)){
            return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").build());
        }else{
            return ResponseEntity.ok().body(ResponseImpl.ok().with(-1, "exist").build());
        }
    }
}
