package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.BinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BinhLuanRepository extends JpaRepository<BinhLuan,Long> {

    @Query(value = "select * from binhluan where MaHinhAnh= :id",nativeQuery = true)
    List<BinhLuan> findBinhLuanByIdHinhAnh(int id);
}
