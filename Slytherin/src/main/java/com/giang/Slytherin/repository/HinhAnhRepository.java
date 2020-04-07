package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh,Long> {

    @Query("select h from HinhAnh h where h.MaHinhAnh =:ma")
    HinhAnh findByMaHinhAnh(long ma);



}
