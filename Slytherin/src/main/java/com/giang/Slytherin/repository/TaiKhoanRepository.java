package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Long> {

    @Query(value = "select * from taikhoan where TenDangNhap= :username",nativeQuery = true)
    TaiKhoan findByTenDangNhap(String username);
}
