package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh,Long> {

    @Query("select h from HinhAnh h where h.MaHinhAnh =:ma")
    HinhAnh findByMaHinhAnh(long ma);

    @Query(value = "select * from hinhanh limit 12",nativeQuery = true)
    List<HinhAnh> findHinhAnhHomeLimit();

    @Query(value = "select * from hinhanh where AnhTaiTro=1 order by rand() limit 6",nativeQuery = true)
    List<HinhAnh> findHinhAnhBySpon();

    @Query(value = "select * from hinhanh where MaBoSuuTap= :id order by rand() limit 6",nativeQuery = true)
    List<HinhAnh> findHinhAnhByCollecLimit(int id);

    @Query(value = "select * from hinhanh where PheDuyet=1 and MaBoSuuTap = :id",nativeQuery = true)
    List<HinhAnh> findListHinhAnhByColl(int id);

    @Query(value = "select * from hinhanh where PheDuyet=1 ",nativeQuery = true)
    List<HinhAnh> findListHinhAnh();
}
