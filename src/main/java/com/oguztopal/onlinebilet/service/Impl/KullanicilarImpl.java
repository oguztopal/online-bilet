package com.oguztopal.onlinebilet.service.Impl;

import com.oguztopal.onlinebilet.entity.Kullanicilar;
import com.oguztopal.onlinebilet.entity.MisafirRequest;
import com.oguztopal.onlinebilet.repository.KullaniciRepository;
import com.oguztopal.onlinebilet.service.IKullanicilarImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KullanicilarImpl implements IKullanicilarImpl {
    private KullaniciRepository kullanicilarDAO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public KullanicilarImpl(KullaniciRepository kullaniciRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.kullanicilarDAO = kullaniciRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Kullanicilar kullaniciGetirById(Long id){
        return kullanicilarDAO.getByKullaniciId(id);
    }

    @Override
    public Kullanicilar kullaniciGetirByUsername(String username){
        return kullanicilarDAO.getByKullaniciAdiAndAktif(username,true);
    }

    @Override
    public Kullanicilar kullaniciEkle(Kullanicilar kullanicilar) {
        if (kullanicilar == null){
            throw  new IllegalArgumentException("Kullanicilar Boş");
        }
        Kullanicilar kul = kullanicilarDAO.getByKullaniciId(kullanicilar.getKullaniciId());
        if (kul == null){
            Kullanicilar yeniKullanici = new Kullanicilar();
            yeniKullanici.setAdi(kullanicilar.getAdi());
            yeniKullanici.setAdres(kullanicilar.getAdres());
            yeniKullanici.setAktif(true);
            yeniKullanici.setKullaniciAdi(kullanicilar.getKullaniciAdi());
            yeniKullanici.setKullaniciTipi("3");
            yeniKullanici.setSoyadi(kullanicilar.getSoyadi());
            return kullanicilarDAO.save(yeniKullanici);
        }
        return kullanicilarDAO.save(kullanicilar);
    }

    @Override
    public Boolean kullaniciSil(Long id) {
        Kullanicilar kul = kullanicilarDAO.getByKullaniciId(id);
        Boolean check = false;
        if (kul!=null){
            try{
                kullanicilarDAO.delete(kul);
                check = true;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            throw new IllegalArgumentException("Böyle Bir Kullanıcı Bulunmamakta");
        }
        return check;
    }

    public Boolean misafir(MisafirRequest misafirRequest) {
        try{
            Kullanicilar kullanicilar = new Kullanicilar();
            Kullanicilar k1 = kullanicilarDAO.findByKullaniciAdiAndAktif(misafirRequest.getKullaniciAdi(),true);
            if (k1!=null){
                throw new IllegalArgumentException("Böyle bir Kullanıcı Sistemde Mevcut");
            }
            kullanicilar.setKullaniciAdi(misafirRequest.getKullaniciAdi());
            kullanicilar.setSifre(bCryptPasswordEncoder.encode(misafirRequest.getSifre()));
            kullanicilar.setEmail(misafirRequest.getEmail());
            kullanicilar.setAdres(misafirRequest.getAdres());
            kullanicilar.setAktif(true);
            kullanicilarDAO.save(kullanicilar);
            return true;
        }catch (Exception ex){
            log.error("");
            return Boolean.FALSE;
        }
    }
}
