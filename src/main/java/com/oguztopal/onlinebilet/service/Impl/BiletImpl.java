package com.oguztopal.onlinebilet.service.Impl;

import com.oguztopal.onlinebilet.entity.*;
import com.oguztopal.onlinebilet.repository.*;
import com.oguztopal.onlinebilet.service.IBiletImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BiletImpl implements IBiletImpl {

    private static final String ALFABE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final BiletRepository biletRepository;

    private final MusterilerRepository musterilerRepository;

    private final HavalimanlariRepository havalimanlariRepository;

    private final UcusseferleriRepository ucusseferleriRepository;

    public BiletImpl(BiletRepository biletRepository, MusterilerRepository musterilerRepository, HavalimanlariRepository havalimanlariRepository, UcusseferleriRepository ucusseferleriRepository) {
        this.biletRepository = biletRepository;
        this.musterilerRepository = musterilerRepository;
        this.havalimanlariRepository = havalimanlariRepository;
        this.ucusseferleriRepository = ucusseferleriRepository;
    }

    @Override
    public Bilet getBiletId(Long id) {
        if (id == null){
            throw new IllegalArgumentException("user cannot be null!!");
        }
        Bilet bilet = biletRepository.getByBiletId(id);
        return bilet;
    }

    @Override
    public Bilet biletOlustur(Bilet bilet) {
        Ucusseferleri ucusseferleri = ucusseferleriRepository.getBySeferIdAndAktif(bilet.getUcusseferleri().getSeferId(),true);
        if (ucusseferleri==null){
            throw new IllegalArgumentException("Böyle bir uçuş seferi bulunmamaktadır yada henüz aktif değildir.");
        }else{
            if (ucusseferleri.getDurum()!=Ucusdurumlari.UCAK_KALKMADI){
                throw new IllegalArgumentException("Uçak seferi için Bilet alım süresi dolmuştur. Lütfen Başka UÇak seferlerini deneyiniz.!");
            }
            Bilet yenibilet = new Bilet();
            Havalimanlari havalimanlari = havalimanlariRepository.getByHavalimaniId(ucusseferleri.getGidis().getHavalimaniId());
            Havalimanlari donus = havalimanlariRepository.getByHavalimaniId(ucusseferleri.getDonus().getHavalimaniId());
            yenibilet.setAktif(true);
            yenibilet.setKalkisSaati(ucusseferleri.getKalkisTarihi());
          /*  TokenResponse token =null;
            Musteriler musteriler = musterilerRepository.getByKullaniciAdi(token.getKullaniciAdi());
            if (musteriler!=null){
                yenibilet.setMusteri_id(musteriler);
            }else{
                throw new IllegalArgumentException("Böyle Bir Müşteri Bulunmamaktadır.");
            }*/
            Bilet pnrKontrol = null;
            String PNR ="";
            Boolean check = true;
            do {
                PNR =  PnrKodOluşturma(5);
                pnrKontrol = biletRepository.getByPNRAndAktif(PNR,check);
            }while(pnrKontrol!=null);
            String Pnrkodu = "PNR"+PNR;
            yenibilet.setPNR(Pnrkodu);
            yenibilet.setYolcuAdSoyad(bilet.getYolcuAdSoyad());
            yenibilet.setYolcuAdres(bilet.getYolcuAdres());
            yenibilet.setYolcuEmail(bilet.getYolcuEmail());
            yenibilet.setYolcuTel(bilet.getYolcuTel());
            yenibilet.setUcusseferleri(ucusseferleri);
            biletRepository.save(yenibilet);
            return  yenibilet;
        }
    }

    public Bilet pnrSorgulama(String pnr){
        if (pnr==null){
            throw new IllegalArgumentException("Pnr boş. Lütfen bir pnr Numarası Giriniz");
        }
        Bilet bilet = biletRepository.getByPNRAndAktif(pnr,true);
        if (bilet == null){
            throw new IllegalArgumentException("Pnr kodu sistemde bulunamadı.");
        }
        return bilet;
    }
    @Transactional
    @Override
    public String biletIptal(Long biletId) {
        if (biletId==null){
            throw new IllegalArgumentException("Bilet Id Boş");
        }
        Bilet bilet = biletRepository.getByBiletIdAndAktif(biletId,true);
        if (bilet==null){
            throw new  IllegalArgumentException("Bilet Aktif değildir");
        }else if (bilet.getUcusseferleri().getIptaldurumu()==false){
            throw new IllegalArgumentException("Bu Uçuş İptal Edilemez.") ;
        }else{
            try{
                 biletRepository.biletIptal(biletId);
                 return "Bilet İptal Olmuştur.";

            }catch (Exception e){
                e.getMessage();
            }
        }
        return "";
    }

    public static String PnrKodOluşturma(int sayac) {
        StringBuilder builder = new StringBuilder();
        while (sayac-- != 0) {
            int character = (int)(Math.random()*ALFABE.length());
            builder.append(ALFABE.charAt(character));
        }
        return builder.toString();
    }


}
