package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.response.BinhLuanData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.service.BinhLuanServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HinhAnhController {
    @Autowired
    HinhAnhServiceImp hinhAnhServiceImp;

    @Autowired
    BinhLuanServiceImp binhLuanServiceImp;

    @GetMapping("/hinhanh/{id}")
    public HinhAnhData findHinhAnhById(@PathVariable("id") long id){
        return hinhAnhServiceImp.findByMaHinhAnh(id,0);
    }

    @GetMapping("/hinhanh/binhluan/{id}")
    public List<BinhLuanData> findBinhLuanByMaHinhAnh(@PathVariable("id") int id){
        return binhLuanServiceImp.findBinhluanByMaHinhAnh(id);
    }
    @GetMapping("/hinhanh/spon")
    public List<HinhAnhData> findHinhAnhBySpon(){
        return hinhAnhServiceImp.findHinhAnhBySpon();
    }
    @GetMapping("/hinhanh/collec/{id}")
    public List<HinhAnhData> findHinhAnhByCollecLimit(@PathVariable("id") int id){
        return hinhAnhServiceImp.findHinhAnhByCollecLimit(id);
    }
}
