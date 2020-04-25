package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.BinhLuanData;
import com.giang.Slytherin.model.BinhLuan;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.repository.BinhLuanRepository;
import com.giang.Slytherin.repository.HinhAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinhLuanServiceImp {

    @Autowired
    BinhLuanRepository binhLuanRepository;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

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
    public boolean xuLyBinhLuan(int mahinhanh,String binhluan){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(customUserDetails!=null){
            BinhLuan binhLuan=new BinhLuan();
            binhLuan.setBinhLuan(binhluan);
            binhLuan.setTaikhoan(customUserDetails.getTaikhoan());
            binhLuan.setHinhanh(hinhAnhRepository.findByMaHinhAnh(mahinhanh));
            binhLuanRepository.save(binhLuan);
            return true;
        }else{
            return false;
        }
    }
}
