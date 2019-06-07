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

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@ToString
@Table(name="takvim")
public class Takvim implements Serializable {
    private static Logger log = LoggerFactory.getLogger(Takvim.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="zaman_id")
    private Long zamanId;

    @Column(name="tarih")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date tarih;

    @Column(name = "gun")
    private int gun;

    @Column(name = "ay")
    private int ay;

    @Column(name = "yil")
    private int yil;

    @Column(name = "aciklama")
    private String aciklama;
}
