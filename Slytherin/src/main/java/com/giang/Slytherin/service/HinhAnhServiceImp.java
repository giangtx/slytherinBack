package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.BinhLuanData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.model.BinhLuan;
import com.giang.Slytherin.model.HinhAnh;
import com.giang.Slytherin.model.Thich;
import com.giang.Slytherin.repository.HinhAnhRepository;
import com.giang.Slytherin.repository.ThichRepository;
import com.sun.org.apache.bcel.internal.generic.DADD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HinhAnhServiceImp {

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    ThichRepository thichRepository;


    public HinhAnhData findByMaHinhAnh(long idHa,int idTk){
        HinhAnhData data=new HinhAnhData();
        HinhAnh hinhAnh=hinhAnhRepository.findByMaHinhAnh(idHa);
        Thich thich=thichRepository.findTrangThaiThichByIdHAandIdTaiKhoan(hinhAnh.getMaHinhAnh(),idTk);
        if(thich!=null){
            data.setTrangthaithich(thich.getTrangThai());
            data.setMathich(thich.getMaThich());
        }
        data.setMahinhanh(hinhAnh.getMaHinhAnh());
        data.setTenhinhanh(hinhAnh.getTenHinhAnh());
        data.setMota(hinhAnh.getMoTaHinhAnh());
        data.setKichco(hinhAnh.getKichCo());
        data.setMabosuutap(hinhAnh.getBosuutap().getMaBoSuuTap());
        data.setTenbosuutap(hinhAnh.getBosuutap().getTenBoSuuTap());
        data.setSolike(hinhAnh.getLikes());
        data.setSobinhluan(hinhAnh.getComments());
        if(hinhAnh.getTaikhoan()!=null){
            data.setTendangnhap(hinhAnh.getTaikhoan().getTenDangNhap());
            data.setAnhdaidien(hinhAnh.getTaikhoan().getAnhDaiDien());
        }
        /*List<BinhLuanData> binhLuans=new ArrayList<>();
        for (BinhLuan binhLuanlist:hinhAnh.getBinhLuans()) {
            BinhLuanData binhluan=new BinhLuanData();
            binhluan.setMabinhluan(binhLuanlist.getMaTuongTac());
            binhluan.setBinhluan(binhLuanlist.getBinhLuan());
            binhLuans.add(binhluan);
        }

        data.setListbinhluan(binhLuans);*/
        return data;
    };
    public List<HinhAnhData>findListHinhAnhFull(){
        List<HinhAnh> listha=hinhAnhRepository.findAll();
        List<HinhAnhData> datas=new ArrayList<HinhAnhData>();
        try {
            for (HinhAnh has:listha) {
                HinhAnhData data=new HinhAnhData();
                data.setMahinhanh(has.getMaHinhAnh());
                data.setTenhinhanh(has.getTenHinhAnh());
                if(has.getTaikhoan()!=null){
                    data.setTendangnhap(has.getTaikhoan().getTenDangNhap());
                    data.setAnhdaidien(has.getTaikhoan().getAnhDaiDien());
                }
                data.setTenbosuutap(has.getBosuutap().getTenBoSuuTap());
                data.setMota(has.getMoTaHinhAnh());
                data.setKichco(has.getKichCo());
                data.setMota(has.getMoTaHinhAnh());

                data.setTrangthaithich(1);
                List<BinhLuanData> bls=new ArrayList<BinhLuanData>();
                for (BinhLuan listbl:has.getBinhLuans()) {
                    BinhLuanData bl=new BinhLuanData();
                    bl.setMabinhluan(listbl.getMaTuongTac());
                    bl.setBinhluan(listbl.getBinhLuan());
                    bls.add(bl);
                }
                data.setListbinhluan(bls);
                datas.add(data);
            }
        }catch (Exception e){

        }

        return datas;
    }
    public List<HinhAnhData> findHinhAnhHomeLimit(){
        List<HinhAnhData> listdata=new ArrayList<>();
        List<HinhAnh> listHinhAnh=hinhAnhRepository.findHinhAnhHomeLimit();
        for (HinhAnh hinhanh: listHinhAnh) {
            HinhAnhData data=new HinhAnhData();
            data.setMahinhanh(hinhanh.getMaHinhAnh());
            data.setTenhinhanh(hinhanh.getTenHinhAnh());
            data.setResize(hinhanh.getResize());
            listdata.add(data);
        }
        return listdata;
    }
    public List<HinhAnhData> findHinhAnhBySpon(){
        List<HinhAnhData> lstdata=new ArrayList<>();
        List<HinhAnh> listHinhAnh=hinhAnhRepository.findHinhAnhBySpon();
        for (HinhAnh hinhanh:listHinhAnh) {
            HinhAnhData data=new HinhAnhData();
            data.setMahinhanh(hinhanh.getMaHinhAnh());
            data.setTenhinhanh(hinhanh.getTenHinhAnh());
            data.setResize(hinhanh.getResize());
            lstdata.add(data);
        }
        return lstdata;
    }
    public List<HinhAnhData> findHinhAnhByCollecLimit(int id){
        List<HinhAnhData> lstdata=new ArrayList<>();
        List<HinhAnh> listHinhAnh=hinhAnhRepository.findHinhAnhByCollecLimit(id);
        for (HinhAnh hinhanh:listHinhAnh) {
            HinhAnhData data=new HinhAnhData();
            data.setMahinhanh(hinhanh.getMaHinhAnh());
            data.setTenhinhanh(hinhanh.getTenHinhAnh());
            data.setResize(hinhanh.getResize());
            lstdata.add(data);
        }
        return lstdata;
    }

    public List<HinhAnhData> findListHinhAnhByColl(int id){
        List<HinhAnhData> lstdata = new ArrayList<>();
        List<HinhAnh> listHinhAnh = hinhAnhRepository.findListHinhAnhByColl(id);
        for (HinhAnh hinhAnh : listHinhAnh) {
            HinhAnhData data = new HinhAnhData();
            data.setMahinhanh(hinhAnh.getMaHinhAnh());
            data.setTenhinhanh(hinhAnh.getTenHinhAnh());
            data.setResize(hinhAnh.getResize());
            lstdata.add(data);
        }
        return lstdata;
    }

    public List<HinhAnhData>findListHinhAnh(){
        List<HinhAnhData> lstdata = new ArrayList<>();
        List<HinhAnh> listHinhAnh = hinhAnhRepository.findListHinhAnh();
        for (HinhAnh hinhanh : listHinhAnh) {
            HinhAnhData data = new HinhAnhData();
            data.setMahinhanh(hinhanh.getMaHinhAnh());
            data.setTenhinhanh(hinhanh.getTenHinhAnh());
            data.setResize(hinhanh.getResize());
            lstdata.add(data);
        }
        return lstdata;
    }
}
