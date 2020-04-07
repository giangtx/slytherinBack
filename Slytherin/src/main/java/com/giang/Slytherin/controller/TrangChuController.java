package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.response.BoSuuTapData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.ListHinhAnhData;
import com.giang.Slytherin.model.BoSuuTap;
import com.giang.Slytherin.model.HinhAnh;
import com.giang.Slytherin.service.BoSuutapServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
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

    @GetMapping("/hinhanh")
    public HinhAnhData HinhAnhTest(){
        return hinhAnhServiceImp.findByMaHinhAnh(1L,61);
    }

    @GetMapping("/listhinhanh")
    public List<HinhAnhData> findListHinhAnh(){
        return hinhAnhServiceImp.findListHinhAnh();
    }

    @GetMapping("/bosuutap")
    public BoSuuTapData findBoSuuTapByID(@RequestParam long id){
        return boSuutapServiceImp.findByMaBoSuuTap(id);
    }

    @GetMapping("/bosuutaplimit")
    public List<BoSuuTapData> findBoSuuTapLimit(){

        return boSuutapServiceImp.findBoSuuTapLimit();
    }
}
