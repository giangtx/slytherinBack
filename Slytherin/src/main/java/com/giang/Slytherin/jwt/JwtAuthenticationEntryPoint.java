package com.giang.Slytherin.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.AuthenticationException e)
            throws IOException, ServletException {
        BaseResponse response=new BaseResponse(401,"Unauthorised");
        response.setMessage("Unauthorised");
        OutputStream out=httpServletResponse.getOutputStream();
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(out,response);
        out.flush();

    }
}