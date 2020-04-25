package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.model.HinhAnh;
import com.giang.Slytherin.model.TaiKhoan;
import com.giang.Slytherin.model.Thich;
import com.giang.Slytherin.repository.HinhAnhRepository;
import com.giang.Slytherin.repository.ThichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ThichServiceImp {

    @Autowired
    ThichRepository thichRepository;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    public HinhAnhData xuLyThich(int mahinhanh){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HinhAnhData data=new HinhAnhData();
        if(customUserDetails!=null){
            Thich thich=thichRepository.findTrangThaiThichByIdHAandIdTaiKhoan(mahinhanh,customUserDetails.getTaikhoan().getMaTaiKhoan());
            HinhAnh hinhAnh=hinhAnhRepository.findByMaHinhAnh(mahinhanh);
            data.setMahinhanh(hinhAnh.getMaHinhAnh());
            data.setTenhinhanh(hinhAnh.getTenHinhAnh());
            data.setMota(hinhAnh.getMoTaHinhAnh());
            data.setKichco(hinhAnh.getKichCo());
            data.setMabosuutap(hinhAnh.getBosuutap().getMaBoSuuTap());
            data.setTenbosuutap(hinhAnh.getBosuutap().getTenBoSuuTap());

            data.setSobinhluan(hinhAnh.getComments());
            if(hinhAnh.getTaikhoan()!=null){
                data.setTendangnhap(hinhAnh.getTaikhoan().getTenDangNhap());
                data.setAnhdaidien(hinhAnh.getTaikhoan().getAnhDaiDien());
            }
            if (thich==null){
                Thich like=new Thich();
                like.setHinhanh(hinhAnh);
                like.setTaikhoan(customUserDetails.getTaikhoan());
                like.setTrangThai(1);
                thichRepository.save(like);
                data.setSolike(hinhAnh.getLikes()+1);
                data.setTrangthaithich(1);
            }else{
                thichRepository.delete(thich);
                data.setSolike(hinhAnh.getLikes()-1);
                data.setTrangthaithich(0);
            }
        }
        return data;
    }
}
