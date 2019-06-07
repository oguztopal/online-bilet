package com.oguztopal.onlinebilet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "musteriler")
public class Musteriler implements Serializable {
    private static Logger log = LoggerFactory.getLogger(Musteriler.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="musteri_id")
    private Long musteriId;

    @Column(name="ad")
    private String ad;

    @JoinColumn(name = "kullanici_adi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Kullanicilar kullaniciAdi;

    @Column(name="soyad")
    private String soyad;

    @Column(name="email")
    private String email;

    @Column(name="tel")
    private String tel;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyyy")
    @Column(name="dogum_tarihi")
    private Date dogumTarihi;

    @Column(name="sifre")
    private String sifre;

    @Column(name="cinsiyet")
    private String cinsiyet;



}
