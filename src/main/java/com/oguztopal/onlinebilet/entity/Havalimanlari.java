package com.oguztopal.onlinebilet.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Entity
@Table(name = "havalimanlari")
public class Havalimanlari implements Serializable {
    private static Logger log = LoggerFactory.getLogger(Havalimanlari.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="havalimani_id")
    private Long havalimaniId;

    @Column(name="sehir")
    private String sehir;

    @Column(name="sehir_kod")
    private String sehirKodu;

    @Column(name="havalimani_adi")
    private String havalimaniAdi;

    @Column(name="adres")
    private String adres;

    @Column(name="ulke")
    private String ulke;

    @Column(name="aktif")
    private Boolean aktif;

    @JsonCreator
    public Havalimanlari(@JsonProperty("havalimaniId") Long havalimaniId){
        this.havalimaniId= havalimaniId;
    }
}
