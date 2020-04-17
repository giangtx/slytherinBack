package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.Thich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThichRepository extends JpaRepository<Thich,Long> {

    @Query("select t from Thich t where MaHinhAnh =:idHa and MaTaiKhoan =:idTk")
    Thich findTrangThaiThichByIdHAandIdTaiKhoan(long idHa, int idTk);
}
