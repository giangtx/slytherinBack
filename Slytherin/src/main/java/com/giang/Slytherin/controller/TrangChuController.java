package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.response.BoSuuTapData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.ListHinhAnhData;
import com.giang.Slytherin.model.BoSuuTap;
import com.giang.Slytherin.model.HinhAnh;
import com.giang.Slytherin.model.TaiKhoan;
import com.giang.Slytherin.service.BoSuutapServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import com.giang.Slytherin.service.TaiKhoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/listhinhanh")
    public List<HinhAnhData> findListHinhAnh(){
        return hinhAnhServiceImp.findListHinhAnhFull();
    }

    @GetMapping("/taikhoantest")
    public void findByTenDangNhap(){
        System.out.println(taiKhoanServiceImp.findByTenDangNhap("slytherin"));
    }
    @GetMapping("/bosuutaplimit")
    public List<BoSuuTapData> findBoSuuTapLimit(){
        return boSuutapServiceImp.findBoSuuTapLimit();
    }

    @GetMapping("/hinhanhhomelimit")
    public List<HinhAnhData> findHinhAnhHomeLimit(){
        return hinhAnhServiceImp.findHinhAnhHomeLimit();
    }
}
