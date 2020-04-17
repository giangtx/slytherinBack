package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.response.BoSuuTapData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.service.BoSuutapServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/bosuutap/{id}")
    public List<HinhAnhData> findListHinhAnhByColl(@PathVariable("id") int id){
        return hinhAnhServiceImp.findListHinhAnhByColl(id);
    }

    @GetMapping("/bosuutap")
    public List<HinhAnhData> findListHinhAnh(){
        return hinhAnhServiceImp.findListHinhAnh();
    }
    @GetMapping("/bosuutap/name")
    public List<BoSuuTapData> findBoSuuTapALl(){
        return boSuutapServiceImp.findBoSuuTapALl();
    }
}
