package com.oguztopal.onlinebilet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="kullanicilar",indexes = {@Index(name = "idx_username",columnList = "kullanici_adi")})
public class Kullanicilar implements Serializable {
        private static Logger log = LoggerFactory.getLogger(Kullanicilar.class);
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="kullanici_id")
        private Long kullaniciId;

        @Column(name="kullanici_adi",nullable = false)
        private String kullaniciAdi;

        @Column(name = "ad_soyad")
        private String adSoyad;

        @Column(name="sifre",nullable = false)
        private String sifre;

        @Column(name = "adi",nullable = true)
        private String adi;
        @Column(name="soyadi",nullable = true)
        private String soyadi;

        @Column(name="aktif",nullable = true)
        private Boolean aktif;

        @Column(name="adres",nullable = true)
        private String adres;

        @Column(name = "telefon",nullable = true)
        private String telefon;

        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @Temporal(TemporalType.DATE)
        @Column(name = "tarih", nullable = true)
        private Date tarih;

        @Column(name="kullanici_tipi",nullable = true , length = 1)
        private String kullaniciTipi;

        @Column(name = "email" ,nullable = true , length = 50)
        private String email;
}
