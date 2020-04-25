package com.giang.Slytherin.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {
        BaseResponse response=new BaseResponse(403,"Access Denied");
        response.setMessage("Access Denied");
        OutputStream out=httpServletResponse.getOutputStream();
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(out,response);
        out.flush();

    }
}
