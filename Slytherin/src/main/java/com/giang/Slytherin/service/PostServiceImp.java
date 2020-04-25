package com.giang.Slytherin.service;

import com.giang.Slytherin.controller.response.PostCommentData;
import com.giang.Slytherin.controller.response.PostData;
import com.giang.Slytherin.model.Post;
import com.giang.Slytherin.model.PostComment;
import com.giang.Slytherin.model.PostLike;
import com.giang.Slytherin.repository.PostCommentRepository;
import com.giang.Slytherin.repository.PostLikeRepository;
import com.giang.Slytherin.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<PostData> findPostAll(int iduser){
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
            PostLike like=postLikeRepository.findPostLikeByPostandUser(post.getIdpost(),iduser);
            if(like!=null){
                data.setStatuslike(like.getStatuslike());
            }
            lstdata.add(data);
        }
        return lstdata;
    }
    public List<PostCommentData> findPostCommentsByIdpost(int idpost){
        List<PostCommentData> lstdata=new ArrayList<>();
        List<PostComment> comments=postCommentRepository.findPostCommentByIdpost(idpost);
        for (PostComment comment:comments){
            PostCommentData data=new PostCommentData();
            data.setId(comment.getIdcomment());
            data.setComment(comment.getCommentcon());
            data.setTimecomment(comment.getTimecomment());
            if(comment.getTaikhoan()!=null){
                data.setUsername(comment.getTaikhoan().getTenDangNhap());
                data.setAvatar(comment.getTaikhoan().getAnhDaiDien());
            }
            lstdata.add(data);
        }
        return lstdata;
    }

}
