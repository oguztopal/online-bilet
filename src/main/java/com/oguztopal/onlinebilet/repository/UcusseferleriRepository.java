package com.oguztopal.onlinebilet.repository;


import com.oguztopal.onlinebilet.entity.Havalimanlari;
import com.oguztopal.onlinebilet.entity.Kupon;
import com.oguztopal.onlinebilet.entity.Ucusdurumlari;
import com.oguztopal.onlinebilet.entity.Ucusseferleri;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UcusseferleriRepository extends JpaRepository<Ucusseferleri,Long> {

    Ucusseferleri getBySeferId(Long id);

    List<Ucusseferleri> getAllBySeferIdIsNotNull();

    List<Ucusseferleri> getUcusseferleriByGidisAndAktif (Long gidis , Long donus);

    Ucusseferleri getBySeferIdAndAktif(Long seferId,Boolean aktif);

    List<Ucusseferleri> getAllByGidisAndDonusAndKalkisTarihi(Long gidis, Long donus , Date kalkis);

    @Query(value = "SELECT u.* FROM ucus_seferleri u WHERE u.havalimani_gidis_id = :gidis and u.havalimani_donus_id = :donus and u.iptal_durumu = :iptaldurumu and to_char(u.kalkis_tarihi,'dd/MM/yyyy') = :tarih" , nativeQuery = true)
    List<Ucusseferleri> ucusSeferleriGetir(
            @Param("gidis") Long gidis ,
            @Param("donus") Long donus,
            @Param("iptaldurumu") Boolean iptal,
            @Param("tarih")String tarih);

    @Modifying(clearAutomatically = true)
    @Query(value="update ucus_seferleri set durum= :durumu where sefer_id = :seferId " , nativeQuery = true)
    void ucusDurumuGuncelle(@Param("durumu") String ucusdurumlari,
                               @Param("seferId") Long seferId);


}