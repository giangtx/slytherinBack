package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.response.BoSuuTapData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.ListHinhAnhData;
import com.giang.Slytherin.controller.response.TaiKhoanData;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import com.giang.Slytherin.model.BoSuuTap;
import com.giang.Slytherin.model.HinhAnh;
import com.giang.Slytherin.model.TaiKhoan;
import com.giang.Slytherin.service.BoSuutapServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import com.giang.Slytherin.service.TaiKhoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrangChuController {

    @Autowired
    HinhAnhServiceImp hinhAnhServiceImp;

    @Autowired
    BoSuutapServiceImp boSuutapServiceImp;

    @Autowired
    TaiKhoanServiceImp taiKhoanServiceImp;

    @GetMapping("/public/listhinhanh")
    public ResponseEntity<BaseResponse<List<HinhAnhData>>> findListHinhAnh(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(hinhAnhServiceImp.findListHinhAnhFull()).build());
    }

    @GetMapping("/public/bosuutaplimit")
    public ResponseEntity<BaseResponse<List<BoSuuTapData>>> findBoSuuTapLimit(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(boSuutapServiceImp.findBoSuuTapLimit()).build());
    }

    @GetMapping("/public/hinhanhhomelimit")
    public ResponseEntity<BaseResponse<List<HinhAnhData>>> findHinhAnhHomeLimit(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(hinhAnhServiceImp.findHinhAnhHomeLimit()).build());
    }

    @GetMapping("/auth/info")
    public ResponseEntity<BaseResponse<TaiKhoanData>> getInfoTest(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(taiKhoanServiceImp.getInfoTest()).build());
    }
}
