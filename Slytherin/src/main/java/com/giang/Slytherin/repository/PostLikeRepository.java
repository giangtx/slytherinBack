package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long> {

    @Query(value = "select * from post_like where idpost =:idpost and MaTaiKhoan =:iduser",nativeQuery = true)
    PostLike findPostLikeByPostandUser(long idpost, long iduser);
}
