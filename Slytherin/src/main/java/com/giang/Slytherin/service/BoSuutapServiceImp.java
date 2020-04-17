package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.BoSuuTapData;
import com.giang.Slytherin.model.BoSuuTap;
import com.giang.Slytherin.repository.BoSuuTapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoSuutapServiceImp {
    @Autowired
    BoSuuTapRepository boSuuTapRepository;

    public BoSuuTapData findByMaBoSuuTap(long id){
        BoSuuTapData boSuuTapData=new BoSuuTapData();
        BoSuuTap boSuuTap=boSuuTapRepository.findByMaBoSuuTap(id);
        boSuuTapData.setMabosuutap(boSuuTap.getMaBoSuuTap());
        boSuuTapData.setTenbosuutap(boSuuTap.getTenBoSuuTap());
        //boSuuTapData.setAnhbosuutap(boSuuTap.getAnhBoSuuTap());
        //boSuuTapData.setMota(boSuuTap.getMoTa());
        return boSuuTapData;
    }
    public List<BoSuuTapData> findBoSuuTapLimit(){

        List<BoSuuTapData> lstdata=new ArrayList<>();
        List<BoSuuTap> boSuuTap=boSuuTapRepository.findBoSuuTapLimit();
        for (BoSuuTap bst:boSuuTap) {
            BoSuuTapData data=new BoSuuTapData();
            data.setMabosuutap(bst.getMaBoSuuTap());
            data.setTenbosuutap(bst.getTenBoSuuTap());
            data.setMota(bst.getMoTa());
            data.setAnhbosuutap(bst.getAnhBoSuuTap());
            lstdata.add(data);
        }
        return lstdata;
    }
    public List<BoSuuTapData> findBoSuuTapALl(){
        List<BoSuuTapData> lstdata=new ArrayList<>();
        List<BoSuuTap> lstbosuutap=boSuuTapRepository.findBoSuuTapALl();
        for (BoSuuTap boSuuTap:lstbosuutap){
            BoSuuTapData data = new BoSuuTapData();
            data.setMabosuutap(boSuuTap.getMaBoSuuTap());
            data.setTenbosuutap(boSuuTap.getTenBoSuuTap());
            lstdata.add(data);
        }
        return lstdata;
    }
}
