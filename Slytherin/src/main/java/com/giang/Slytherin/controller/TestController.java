package com.giang.Slytherin.controller;

import com.giang.Slytherin.controller.base.BaseController;
import com.giang.Slytherin.controller.request.BinhLuanRequest;
import com.giang.Slytherin.controller.request.LoginRequest;
import com.giang.Slytherin.controller.request.ThichRequest;
import com.giang.Slytherin.controller.response.*;
import com.giang.Slytherin.controller.response.base.BaseResponse;
import com.giang.Slytherin.controller.response.base.ResponseImpl;
import com.giang.Slytherin.jwt.JwtTokenProvider;
import com.giang.Slytherin.model.HinhAnh;
import com.giang.Slytherin.service.HinhAnhServiceImp;
import com.giang.Slytherin.service.PostServiceImp;
import com.giang.Slytherin.service.TaiKhoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class TestController extends BaseController {

    @Autowired
    HinhAnhServiceImp hinhAnhServiceImp;
    @Autowired
    TaiKhoanServiceImp taiKhoanServiceImp;

    @Autowired
    PostServiceImp postServiceImp;

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public String randomStuff(){
        return "JWT Hợp lệ mới có thể thấy được message này";
    }

    @GetMapping("/hinhanh/{id}")
    public ResponseEntity<BaseResponse<HinhAnhData>> findHinhAnhById(@PathVariable("id") long id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").with(hinhAnhServiceImp.findByMaHinhAnh(id,0)).build());
    }

    @GetMapping("/admin")
    public String adminTest(){
        return "admin hi";
    }

    @GetMapping("/user")
    public String userTest(){
        return "user";
    }

    @GetMapping("/useradmin")
    public ResponseEntity<BaseResponse<String>> userAminTest(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").with("user và admin").build());
    }
    @GetMapping("/testpost")
    public String testPost(){
        System.out.println("post: ");
        postServiceImp.findAllPost().forEach(System.out::println);
        System.out.println("like: ");
        System.out.println(postServiceImp.findPostLike(1,61));
        System.out.println("com: ");
        postServiceImp.findPostComment(1).forEach((System.out::println));
        return "test";
    }

    @GetMapping("/post/findall")
    public ResponseEntity<BaseResponse<List<PostData>>> findPostAll(){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").with(postServiceImp.findPostAll()).build());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BaseResponse<PostData>> findPostById(@Valid @PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").with(postServiceImp.findPostById(id)).build());
    }

    @GetMapping("/post/comment/{id}")
    public ResponseEntity<BaseResponse<List<PostCommentData>>> findCommentPost(@Valid @PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok").with(postServiceImp.findPostCommentsByIdpost(id)).build());
    }

    @PostMapping("/post/comment/commenthandle")
    public ResponseEntity<BaseResponse<List<PostCommentData>>> commentHandle(@RequestBody BinhLuanRequest binhLuanRequest){
        if (postServiceImp.commentHandle(binhLuanRequest)){
            return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                    .with(postServiceImp.findPostCommentsByIdpost(binhLuanRequest.getMahinhanh())).build());
        }else{
            return ResponseEntity.ok().body(ResponseImpl.ok().with(-1,"false").build());
        }
    }
    @PostMapping("/post/like")
    public ResponseEntity<BaseResponse<PostData>> likeHandle(@Valid @RequestBody ThichRequest thichRequest){
        return ResponseEntity.ok().body(ResponseImpl.ok().with(1,"ok")
                .with(postServiceImp.likeHandle(thichRequest.getMahinhanh())).build());

    }
}
