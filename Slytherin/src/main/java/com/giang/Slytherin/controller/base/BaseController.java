package com.giang.Slytherin.controller.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    private ObjectMapper mapper;

    @Autowired
    public BaseController() {
        mapper = new ObjectMapper();
    }

}
