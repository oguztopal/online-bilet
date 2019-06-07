package com.oguztopal.onlinebilet.repository;


import com.oguztopal.onlinebilet.entity.Bilet;
import com.oguztopal.onlinebilet.entity.Ucusseferleri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BiletRepository extends JpaRepository<Bilet,Long> {

    Bilet getByBiletId(Long id);

    Bilet getByPNRAndAktif(String PNR,Boolean aktif);

    Bilet getByBiletIdAndAktif(Long id , Boolean aktif);

    @Modifying
    @Query(value = "update bilet set aktif = false where bilet_id = :biletId" , nativeQuery = true)
    void biletIptal(
            @Param("biletId") Long biletId);

}