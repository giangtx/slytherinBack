package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Long> {

    @Query(value = "select * from post_comment where idpost =:idpost",nativeQuery = true)
    List<PostComment> findPostCommentByIdpost(long idpost);
}
