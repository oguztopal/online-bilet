package com.oguztopal.onlinebilet.entity;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicUpdate
@DynamicInsert
@Table(name="ucus_seferleri")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Ucusseferleri implements Serializable {
    private static Logger log = LoggerFactory.getLogger(Ucusseferleri.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sefer_id" , nullable = false)
    private Long seferId;

    @JoinColumn(name="havalimani_gidis_id")
    @ManyToOne(optional = false)
    private Havalimanlari gidis;

    @JoinColumn(name="havalimani_donus_id")
    @ManyToOne(optional = false)
    private Havalimanlari donus;

    @JoinColumn(name = "zaman_id")
    @ManyToOne
    private Takvim takvim;

    @Temporal(TemporalType.TIMESTAMP)
    private Date kalkisTarihi;

    @Column(name="aktif")
    private Boolean aktif;

    @JoinColumn(name="sirket_id")
    @ManyToOne
    private Sirketler sirketler;

    @Column(name="durum")
    @Enumerated(EnumType.STRING)
    private Ucusdurumlari durum;
    @Column(name = "fiyat")
    private double biletFiyati;

    @Column(name = "tahmini_sure")
    private Long tahminiSure;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="HH:mm")
    @Column(name="kalkis")
    private Date kalkis;

    @JsonFormat(pattern="HH:mm")
    @Temporal(TemporalType.TIME)
    @Column(name="varis")
    private Date varis;

    @Column(name="iptal_durumu")
    private Boolean iptaldurumu; //1 iptal edilebilir 0 edilemez.


}
