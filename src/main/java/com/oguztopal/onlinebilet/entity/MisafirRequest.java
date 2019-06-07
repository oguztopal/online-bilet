package com.oguztopal.onlinebilet.entity;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class MisafirRequest {
    private static Logger log = LoggerFactory.getLogger(MisafirRequest.class);
    private String adSoyad;

    private String kullaniciAdi;

    private String sifre;

    private String email;

    private String adres;

    private String telefon;

}
