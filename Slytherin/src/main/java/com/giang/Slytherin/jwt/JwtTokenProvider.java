package com.giang.Slytherin.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.giang.Slytherin.constant.ApiErrorCode;
import com.giang.Slytherin.controller.base.ApiException;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.utils.TextUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "giang";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    public String generateToken(Authentication authentication) {
        CustomUserDetails userDetails=(CustomUserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        return JWT.create()
                .withClaim("userId", userDetails.getTaikhoan().getMaTaiKhoan())
                .withClaim("expired", expiryDate)
                .withClaim("fullName", userDetails.getTaikhoan().getHoTen())
                .withClaim("email", userDetails.getTaikhoan().getEmail())
                .withIssuer("auth0")
                .sign(algorithm);
    }
    public Long getUserIdFromJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0").build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("userId").asLong();
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0").build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
