package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.BinhLuanData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.HinhAnhResponse;
import com.giang.Slytherin.controller.response.ListHinhAnhResponse;
import com.giang.Slytherin.model.BinhLuan;
import com.giang.Slytherin.model.HinhAnh;
import com.giang.Slytherin.model.Thich;
import com.giang.Slytherin.repository.HinhAnhRepository;
import com.giang.Slytherin.repository.ThichRepository;
import com.sun.org.apache.bcel.internal.generic.DADD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class HinhAnhServiceImp {

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    ThichRepository thichRepository;


    public HinhAnhData findByMaHinhAnh(long idHa,long idTk){
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
                    bl.setAnhdaidien(listbl.getTaikhoan().getAnhDaiDien());
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

    public ListHinhAnhResponse findListHinhAnhByColl(int id,int idPage){
        ListHinhAnhResponse response=new ListHinhAnhResponse();
        List<HinhAnhResponse> lstdata = new ArrayList<>();
        Page<HinhAnh> listHinhAnh = hinhAnhRepository.findListHinhAnhByColl(id,PageRequest.of(idPage,10));
        for (HinhAnh hinhAnh : listHinhAnh) {
            HinhAnhResponse data = new HinhAnhResponse();
            data.setMahinhanh(hinhAnh.getMaHinhAnh());
            data.setResize(hinhAnh.getResize());
            lstdata.add(data);
        }
        response.setSotrang(listHinhAnh.getTotalPages());
        response.setListhinhanh(lstdata);
        return response;
    }

    public ListHinhAnhResponse findListHinhAnh(int id){
        ListHinhAnhResponse response=new ListHinhAnhResponse();
        List<HinhAnhResponse> lstdata = new ArrayList<>();
        Page<HinhAnh> listHinhAnh = hinhAnhRepository.findListHinhAnh(PageRequest.of(id,10));
        for (HinhAnh hinhanh : listHinhAnh) {
            HinhAnhResponse data = new HinhAnhResponse();
            data.setMahinhanh(hinhanh.getMaHinhAnh());
            data.setResize(hinhanh.getResize());
            lstdata.add(data);
        }
        response.setSotrang(listHinhAnh.getTotalPages());
        response.setListhinhanh(lstdata);
        return response;
    }

}
