package com.oguztopal.onlinebilet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "kupon")
public class Kupon {
    private static Logger log = LoggerFactory.getLogger(Kupon.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="kupon_id")
    private Long kuponId;
    @Column(name = "kupon_kodu")
    private String kuponKodu;
    @Column(name="indirim")
    private Long indirim;
    @Column(name="aktif")
    private Boolean aktif;
    @Column(name="adet")
    private int adet;
}
