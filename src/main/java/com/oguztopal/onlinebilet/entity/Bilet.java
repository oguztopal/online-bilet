package com.oguztopal.onlinebilet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="bilet")
@Data //getter setter oluşturur.
@NoArgsConstructor  //boş constructer oluşturur
@AllArgsConstructor //dolu cons
@ToString //toString metdou oluşturur.
@EqualsAndHashCode //HashCode
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bilet implements Serializable {
    private static Logger log = LoggerFactory.getLogger(Bilet.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bilet_id")
    private Long biletId;

    @Column(name = "PNR")
    private String pNR;

    @ManyToOne()
    @JoinColumn(name="musteri_id")
    private Musteriler musteri_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="kalkis_tarihi")
    private Date kalkisTarihi;

    @Column(name="kalkis_saati")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kalkisSaati;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sefer_id")
    private Ucusseferleri ucusseferleri;

    @Column(name = "yolcu_ad_soyad")
    private String yolcuAdSoyad;

    @Column(name="yolcu_tel")
    private String yolcuTel;

    @Column(name = "yolcu_email")
    private String yolcuEmail;

    @Column(name = "yolcu_adres")
    private String yolcuAdres;

    @Column(name = "aktif")
    private Boolean aktif;


}
