package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.response.BoSuuTapData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import com.giang.Slytherin.service.BoSuutapServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BosuutapController {

    @Autowired
    HinhAnhServiceImp hinhAnhServiceImp;

    @Autowired
    BoSuutapServiceImp boSuutapServiceImp;

    @GetMapping("/public/bosuutap/{id}")
    public ResponseEntity<BaseResponse<List<HinhAnhData>>> findListHinhAnhByColl(@PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(hinhAnhServiceImp.findListHinhAnhByColl(id)).build());
    }

    @GetMapping("/public/bosuutap")
    public ResponseEntity<BaseResponse<List<HinhAnhData>>> findListHinhAnh(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(hinhAnhServiceImp.findListHinhAnh()).build());
    }
    @GetMapping("/public/bosuutap/name")
    public ResponseEntity<BaseResponse<List<BoSuuTapData>>> findBoSuuTapALl(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(boSuutapServiceImp.findBoSuuTapALl()).build());
    }
}
