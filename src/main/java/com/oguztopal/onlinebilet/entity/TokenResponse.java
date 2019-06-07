package com.oguztopal.onlinebilet.entity;

import jdk.nashorn.internal.parser.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private static Logger log = LoggerFactory.getLogger(TokenResponse.class);
    private String kullaniciAdi;

    private String token;
}
