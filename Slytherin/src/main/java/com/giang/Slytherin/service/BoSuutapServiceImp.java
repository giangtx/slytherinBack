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

        List<BoSuuTapData> lstBoSuuTap=new ArrayList<>();
        List<BoSuuTap> boSuuTap=boSuuTapRepository.findBoSuuTapLimit();
        for (BoSuuTap bst:boSuuTap) {
            BoSuuTapData boSuuTapData=new BoSuuTapData();
            boSuuTapData.setMabosuutap(bst.getMaBoSuuTap());
            boSuuTapData.setTenbosuutap(bst.getTenBoSuuTap());
            boSuuTapData.setMota(bst.getMoTa());
            boSuuTapData.setAnhbosuutap(bst.getAnhBoSuuTap());
            lstBoSuuTap.add(boSuuTapData);
        }
        return lstBoSuuTap;
    }
}
