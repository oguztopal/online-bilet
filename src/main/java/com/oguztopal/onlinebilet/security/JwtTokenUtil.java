package com.oguztopal.onlinebilet.security;

import com.oguztopal.onlinebilet.entity.Kullanicilar;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

/**
 * Created by temelt on 15.02.2019.
 */
@Component
public class JwtTokenUtil {

    // 1Gün
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60 * 1000; //ms 1gün
    public static final String SIGNING_KEY = "ouztopal";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Kullanicilar kullanicilar) {
        return doGenerateToken(kullanicilar.getKullaniciAdi(),kullanicilar);
    }

    private String doGenerateToken(String subject,Kullanicilar kullanicilar) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("durum", Arrays.asList(new SimpleGrantedAuthority("USER")));
        claims.put("kullaniciId",kullanicilar.getKullaniciId().toString());
        claims.put("email",kullanicilar.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://temelt.com")
                .setIssuedAt(new Date(System.currentTimeMillis())) //giriş
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS)) //bitececk
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

}