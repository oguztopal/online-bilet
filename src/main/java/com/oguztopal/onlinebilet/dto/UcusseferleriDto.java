package com.oguztopal.onlinebilet.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oguztopal.onlinebilet.entity.Havalimanlari;
import com.oguztopal.onlinebilet.entity.Sirketler;
import com.oguztopal.onlinebilet.entity.Takvim;
import com.oguztopal.onlinebilet.entity.Ucusdurumlari;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UcusseferleriDto{
    private Long seferId;
    @JsonProperty("gidis")
    private Havalimanlari gidis;
    private Havalimanlari donus;
    private Takvim takvim;
    private Date kalkisTarihi;
    private Boolean aktif;
    private Sirketler sirketler;
    private Ucusdurumlari durum;
    private double biletFiyati;
    private Long tahminiSure;
    private Date kalkis;
    private Date varis;
    @JsonCreator
    public UcusseferleriDto(@JsonProperty("gidis") Havalimanlari gidis, @JsonProperty("donus") Havalimanlari donus,
                            @JsonProperty("kalkisTarihi") Date kalkisTarihi){
        this.gidis= gidis;
        this.donus = donus;
        this.kalkisTarihi = kalkisTarihi;
    }
}
