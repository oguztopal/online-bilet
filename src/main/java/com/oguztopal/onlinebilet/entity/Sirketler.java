package com.oguztopal.onlinebilet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sirketler")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sirketler implements Serializable {
    private static Logger log = LoggerFactory.getLogger(Sirketler.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sirket_id")
    private Long sirketId;
    @Column(name="sirket_adi",nullable = false)
    private String sirketAdi;

}
