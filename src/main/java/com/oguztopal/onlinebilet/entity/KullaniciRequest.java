package com.oguztopal.onlinebilet.entity;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class KullaniciRequest {
    private static Logger log = LoggerFactory.getLogger(KullaniciRequest.class);
    private String kullaniciAdi;

    private String sifre;

}
