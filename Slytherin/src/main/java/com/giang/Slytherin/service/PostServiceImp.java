package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.request.BinhLuanRequest;
import com.giang.Slytherin.controller.response.HinhAnhData;
import com.giang.Slytherin.controller.response.PostCommentData;
import com.giang.Slytherin.controller.response.PostData;
import com.giang.Slytherin.model.*;
import com.giang.Slytherin.repository.PostCommentRepository;
import com.giang.Slytherin.repository.PostLikeRepository;
import com.giang.Slytherin.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostLikeRepository postLikeRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    public List<Post> findAllPost(){
        return postRepository.findAll();
    }
    public PostLike findPostLike(int idpost, int iduser){
        return postLikeRepository.findPostLikeByPostandUser(idpost,iduser);
    }
    public List<PostComment> findPostComment(int idpost){
        return postCommentRepository.findPostCommentByIdpost(idpost);
    }

    public List<PostData> findPostAll(){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PostData> lstdata=new ArrayList<>();
        List<Post> posts=postRepository.findAll();
        for (Post post:posts) {
            PostData data=new PostData();
            data.setIdpost(post.getIdpost());
            data.setContent(post.getContent());
            data.setLikes(post.getLikes());
            data.setComments(post.getComments());
            data.setTimepost(post.getTimepost());
            if(post.getTaikhoan()!=null){
                data.setUsername(post.getTaikhoan().getTenDangNhap());
                data.setAvatar(post.getTaikhoan().getAnhDaiDien());
            }
            PostLike like=postLikeRepository.findPostLikeByPostandUser(post.getIdpost(),customUserDetails.getTaikhoan().getMaTaiKhoan());
            if(like!=null){
                data.setStatuslike(like.getStatuslike());
            }
            lstdata.add(data);
        }
        return lstdata;
    }
    public PostData findPostById(int id){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post=postRepository.findById(id);
        PostData data=new PostData();
        data.setIdpost(post.getIdpost());
        data.setContent(post.getContent());
        data.setComments(post.getComments());
        data.setLikes(post.getLikes());
        if (post.getTaikhoan()!=null){
            data.setUsername(post.getTaikhoan().getTenDangNhap());
            data.setAvatar(post.getTaikhoan().getAnhDaiDien());
        }
        PostLike postLike=postLikeRepository.findPostLikeByPostandUser(post.getIdpost(),customUserDetails.getTaikhoan().getMaTaiKhoan());
        if (postLike != null){
            data.setStatuslike(postLike.getStatuslike());
        }
        return data;
    }
    public List<PostCommentData> findPostCommentsByIdpost(int idpost){
        List<PostCommentData> lstdata=new ArrayList<>();
        List<PostComment> comments=postCommentRepository.findPostCommentByIdpost(idpost);
        for (PostComment comment:comments){
            PostCommentData data=new PostCommentData();
            data.setId(comment.getIdcomment());
            data.setComment(comment.getContent());
            data.setTimecomment(comment.getTimecomment());
            if(comment.getTaikhoan()!=null){
                data.setUsername(comment.getTaikhoan().getTenDangNhap());
                data.setAvatar(comment.getTaikhoan().getAnhDaiDien());
            }
            lstdata.add(data);
        }
        return lstdata;
    }

    public boolean commentHandle(BinhLuanRequest binhLuanRequest){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (customUserDetails!=null){
            PostComment postComment=new PostComment();
            postComment.setContent(binhLuanRequest.getBinhluan());
            postComment.setTaikhoan(customUserDetails.getTaikhoan());
            postComment.setPost(postRepository.findById(binhLuanRequest.getMahinhanh()));
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            postComment.setTimecomment(date);
            postCommentRepository.save(postComment);
            return true;
        }else{
            return false;
        }
    }
    public PostData likeHandle(int idpost){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostData data=new PostData();
        if(customUserDetails!=null){
            Post post=postRepository.findById(idpost);
            data.setIdpost(post.getIdpost());
            data.setContent(post.getContent());
            data.setComments(post.getComments());
            if (post.getTaikhoan()!=null){
                data.setUsername(post.getTaikhoan().getTenDangNhap());
                data.setAvatar(post.getTaikhoan().getAnhDaiDien());
            }
            PostLike postLike=postLikeRepository.findPostLikeByPostandUser(idpost,customUserDetails.getTaikhoan().getMaTaiKhoan());
            if (postLike == null){
                PostLike like=new PostLike();
                like.setPost(post);
                like.setTaikhoan(customUserDetails.getTaikhoan());
                like.setStatuslike(1);
                postLikeRepository.save(like);
                data.setStatuslike(1);
                data.setLikes(post.getLikes()+1);
            }else{
                postLikeRepository.delete(postLike);
                data.setStatuslike(0);
                data.setLikes(post.getLikes()-1);
            }
        }
        return data;
    }
}
