package com.oguztopal.onlinebilet.service.Impl;

import com.oguztopal.onlinebilet.entity.Kullanicilar;
import com.oguztopal.onlinebilet.repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class KullaniciDetayServiceImpl implements UserDetailsService {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Kullanicilar kullanicilar = kullaniciRepository.findByKullaniciAdiAndAktif(username,true);
        if(kullanicilar == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(kullanicilar.getKullaniciAdi(), kullanicilar.getSifre(), Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
