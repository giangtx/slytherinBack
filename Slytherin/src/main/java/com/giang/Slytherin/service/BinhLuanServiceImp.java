package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.BinhLuanData;
import com.giang.Slytherin.model.BinhLuan;
import com.giang.Slytherin.repository.BinhLuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinhLuanServiceImp {

    @Autowired
    BinhLuanRepository binhLuanRepository;

    public List<BinhLuanData> findBinhluanByMaHinhAnh(int id){
        List<BinhLuanData> lstDaTa=new ArrayList<>();
        List<BinhLuan> lstBinhLuan=binhLuanRepository.findBinhLuanByIdHinhAnh(id);
        for (BinhLuan bl:lstBinhLuan) {
            BinhLuanData data=new BinhLuanData();
            data.setMabinhluan(bl.getMaTuongTac());
            data.setBinhluan(bl.getBinhLuan());
            data.setTentaikhoan(bl.getTaikhoan().getTenDangNhap());
            data.setAnhdaidien(bl.getTaikhoan().getAnhDaiDien());
            lstDaTa.add(data);
        }
        return lstDaTa;
    }
}
