package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select * from post where idpost =:id",nativeQuery = true)
    Post findById(long id);
}
