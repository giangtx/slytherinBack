package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.request.BinhLuanRequest;
import com.giang.Slytherin.controller.request.ThichRequest;
import com.giang.Slytherin.controller.response.BinhLuanData;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import com.giang.Slytherin.model.CustomUserDetails;
import com.giang.Slytherin.service.BinhLuanServiceImp;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import com.giang.Slytherin.service.ThichServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HinhAnhController {
    @Autowired
    HinhAnhServiceImp hinhAnhServiceImp;

    @Autowired
    BinhLuanServiceImp binhLuanServiceImp;

    @Autowired
    ThichServiceImp thichServiceImp;

    @GetMapping("/public/hinhanh/{id}")
    public ResponseEntity<BaseResponse<HinhAnhData>> findHinhAnhById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseImpl.ok()
                .with(1,"ok").with(hinhAnhServiceImp.findByMaHinhAnh(id,0L)).build());
    }
    @GetMapping("/auth/hinhanh/{id}")
    public ResponseEntity<BaseResponse<HinhAnhData>> findHinhAnhByIdAuth(@PathVariable("id") long id){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(customUserDetails!=null){
            return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                    .with(hinhAnhServiceImp.findByMaHinhAnh(id,customUserDetails.getTaikhoan().getMaTaiKhoan())).build());
        }else{
            return ResponseEntity.ok().body(ResponseImpl.ok().with(-1,"false").build());
        }


    }

    @GetMapping("/public/hinhanh/binhluan/{id}")
    public ResponseEntity<BaseResponse<List<BinhLuanData>>> findBinhLuanByMaHinhAnh(@PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(binhLuanServiceImp.findBinhluanByMaHinhAnh(id)).build());
    }
    @GetMapping("/public/hinhanh/spon")
    public ResponseEntity<BaseResponse<List<HinhAnhData>>> findHinhAnhBySpon(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(hinhAnhServiceImp.findHinhAnhBySpon()).build());
    }
    @GetMapping("/public/hinhanh/collec/{id}")
    public ResponseEntity<BaseResponse<List<HinhAnhData>>> findHinhAnhByCollecLimit(@PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(hinhAnhServiceImp.findHinhAnhByCollecLimit(id)).build());
    }
    @PostMapping("/auth/binhluan")
    public ResponseEntity<BaseResponse<BinhLuanData>> xuLyBinhLuan(@Valid @RequestBody BinhLuanRequest binhLuanRequest){
        if(binhLuanServiceImp.xuLyBinhLuan(binhLuanRequest.getMahinhanh(),binhLuanRequest.getBinhluan())){
            return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                    .with(binhLuanServiceImp.findBinhluanByMaHinhAnh(binhLuanRequest.getMahinhanh())).build());
        }
        else {
            return ResponseEntity.ok().body(ResponseImpl.ok().with(-1,"false").build());
        }
    }
    @PostMapping("/auth/thich")
    public ResponseEntity<BaseResponse<HinhAnhData>> xuLyThich(@Valid @RequestBody ThichRequest thichRequest){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").with(thichServiceImp.xuLyThich(thichRequest.getMahinhanh())).build());

    }
}
