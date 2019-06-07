package com.oguztopal.onlinebilet.repository;


import com.oguztopal.onlinebilet.entity.KullaniciRequest;
import com.oguztopal.onlinebilet.entity.Kullanicilar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRepository extends JpaRepository<Kullanicilar,Long> {

        Kullanicilar getByKullaniciId(Long id);

        Kullanicilar findByKullaniciAdi(String kullaniciAdi);

        Kullanicilar findByKullaniciAdiAndAktif(String kullaniciAdi,Boolean aktif);

}