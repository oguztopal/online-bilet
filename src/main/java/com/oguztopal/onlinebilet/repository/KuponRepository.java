package com.oguztopal.onlinebilet.repository;


import com.oguztopal.onlinebilet.entity.Bilet;
import com.oguztopal.onlinebilet.entity.Kupon;
import com.oguztopal.onlinebilet.entity.Ucusseferleri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface KuponRepository extends JpaRepository<Kupon,Long> {


    @Query(value = "select k.* from kupon k where k.kupon_kodu= :kuponkodu and k.aktif = true" , nativeQuery = true)
    Kupon kuponsorgula(@Param("kuponkodu") String kuponKodu);

}