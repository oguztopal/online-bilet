package com.oguztopal.onlinebilet.api;

import com.oguztopal.onlinebilet.entity.KullaniciRequest;
import com.oguztopal.onlinebilet.entity.Kullanicilar;
import com.oguztopal.onlinebilet.entity.MisafirRequest;
import com.oguztopal.onlinebilet.entity.TokenResponse;
import com.oguztopal.onlinebilet.repository.KullaniciRepository;
import com.oguztopal.onlinebilet.security.JwtTokenUtil;
import com.oguztopal.onlinebilet.service.Impl.KullanicilarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
public class HesapController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private KullaniciRepository kullaniciRepository;
    @Autowired
    private KullanicilarImpl kullanicilarService;

    @RequestMapping(value = "/giris" , method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> giris(@RequestBody KullaniciRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getKullaniciAdi(), request.getSifre()));
        final Kullanicilar kullanicilar = kullaniciRepository.findByKullaniciAdiAndAktif(request.getKullaniciAdi(),true);
        final String token = jwtTokenUtil.generateToken(kullanicilar);
        return ResponseEntity.ok(new TokenResponse(kullanicilar.getKullaniciAdi(),token));
    }

    @RequestMapping(value = "/misafir", method = RequestMethod.POST)
    public ResponseEntity<Boolean> misafir(@RequestBody MisafirRequest misafirRequest) throws AuthenticationException {
        Boolean response = kullanicilarService.misafir(misafirRequest);
        return ResponseEntity.ok(response);
    }
}
/*
new TokenResponse(kullanicilar.getKullaniciAdi()*/
