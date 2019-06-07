package com.oguztopal.onlinebilet.repository;

import com.oguztopal.onlinebilet.entity.Musteriler;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MusterilerRepository extends JpaRepository<Musteriler,Long> {

     Musteriler getMusterilerByMusteriId(Long id);

     Musteriler getByKullaniciAdi(String kullaniciAdi);
}