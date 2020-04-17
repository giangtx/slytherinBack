package com.giang.Slytherin.repository;

import com.giang.Slytherin.model.BoSuuTap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoSuuTapRepository extends JpaRepository<BoSuuTap,Long> {
    List<BoSuuTap> findAll();

    @Query("select b from BoSuuTap b where b.MaBoSuuTap =:ma")
    BoSuuTap findByMaBoSuuTap(long ma);

    @Query(value = "select * from bosuutap limit 6",nativeQuery = true)
    List<BoSuuTap> findBoSuuTapLimit();

    @Query(value = "select * from bosuutap",nativeQuery = true)
    List<BoSuuTap> findBoSuuTapALl();
}
