package com.oguztopal.onlinebilet.service.Impl;

import com.oguztopal.onlinebilet.dto.UcusseferleriDto;
import com.oguztopal.onlinebilet.entity.*;
import com.oguztopal.onlinebilet.repository.HavalimanlariRepository;
import com.oguztopal.onlinebilet.repository.KuponRepository;
import com.oguztopal.onlinebilet.repository.SirketlerRepository;
import com.oguztopal.onlinebilet.repository.UcusseferleriRepository;
import com.oguztopal.onlinebilet.service.IUcusseferleriImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UcusseferleriImpl implements IUcusseferleriImpl {


    private final UcusseferleriRepository ucusseferleriRepository;

    private final KuponRepository kuponRepository;

    private final SirketlerRepository sirketlerRepository;

    private final HavalimanlariRepository havalimanlariRepository;

    private final ModelMapper modelMapper;

    public UcusseferleriImpl(UcusseferleriRepository ucusseferleriRepository, KuponRepository kuponRepository, SirketlerRepository sirketlerRepository, HavalimanlariRepository havalimanlariRepository, ModelMapper modelMapper) {
        this.ucusseferleriRepository = ucusseferleriRepository;
        this.kuponRepository = kuponRepository;
        this.sirketlerRepository = sirketlerRepository;
        this.havalimanlariRepository = havalimanlariRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Ucusseferleri getUcusSeferi(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Sefer numarası Boş olamaz");
        }
        Ucusseferleri ucusseferleri = ucusseferleriRepository.getBySeferId(id);
        if (ucusseferleri==null){
            throw new IllegalArgumentException("Böyle bir Uçuş Seferi Bulunmamakta");
        }
        return ucusseferleri;
    }
    @Override
    public List<Ucusseferleri> butunUcusSeferleri(){
        return ucusseferleriRepository.getAllBySeferIdIsNotNull();
    }

    @Override
    public List<UcusseferleriDto> getUcusSeferiByGidisAndDonus(UcusseferleriDto ucusseferleri) {
        return null;
    }

    @Override
    public List<UcusseferleriDto> gidisveDonusUcusSeferleri(Long gidis,Long varis, Date kalkisTarihi,Boolean iptal) throws ParseException {
          SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
          String tarih = sdf.format(kalkisTarihi);
        //Date ucusZamani = sdf.parse(tarih);
        if (gidis == null || varis ==null ){
            throw new IllegalArgumentException("Gidis , Donus ve Tarih Boş olaamazz");
        }
        List<Ucusseferleri> ucusseferleris = ucusseferleriRepository.ucusSeferleriGetir(gidis,varis,iptal,tarih);
        if (ucusseferleris.size()<1){
            throw new IllegalArgumentException("Uçuş Seferi Bulunmamaktadır.");
        }
        return Arrays.asList(modelMapper.map(ucusseferleris,UcusseferleriDto[].class));
    }

    @Transactional
    public Boolean durumGuncelle(Ucusdurumlari ucusdurumlari,Ucusseferleri ucusseferleri){
        Boolean kontrol = false;
        try {
            ucusseferleriRepository.ucusDurumuGuncelle(ucusdurumlari.name(),ucusseferleri.getSeferId());
            kontrol=true;
        }catch (Exception e){
            e.getMessage();
        }
        return kontrol;
    }
    @Override
    public Ucusseferleri ucusseferleriguncelle(Ucusseferleri ucusseferleri){
        if (ucusseferleri==null){
            throw new IllegalArgumentException("Ucus Seferleri Boş");
        }
        if (ucusseferleri.getDonus().getHavalimaniId()==ucusseferleri.getGidis().getHavalimaniId()){
            throw new IllegalArgumentException("İki Havalimanı Aynı seçilemez.");
        }
        Ucusseferleri yeni = ucusseferleriRepository.getBySeferId(ucusseferleri.getSeferId());
        if (yeni!=null){
            try {
                yeni.setAktif(ucusseferleri.getAktif());
                yeni.setBiletFiyati(ucusseferleri.getBiletFiyati());
                Havalimanlari donus = havalimanlariRepository.getByHavalimaniId(ucusseferleri.getDonus().getHavalimaniId());
                if (donus!=null){
                    yeni.setDonus(donus);
                }
                Havalimanlari gidis = havalimanlariRepository.getByHavalimaniId(ucusseferleri.getGidis().getHavalimaniId());
                if (gidis!=null){
                    yeni.setGidis(gidis);
                }
                yeni.setDurum(Ucusdurumlari.UCAK_KALKMADI);
                yeni.setKalkis(ucusseferleri.getKalkis());
                yeni.setVaris(ucusseferleri.getVaris());
                Sirketler sirketler = sirketlerRepository.getBySirketId(ucusseferleri.getSirketler().getSirketId());
                yeni.setSirketler(sirketler);
                yeni.setIptaldurumu(ucusseferleri.getIptaldurumu());
                ucusseferleriRepository.save(yeni);
                return  yeni;
            }catch (Exception ex){
                ex.getMessage();
            }

        }


        return null;
    }

    @Override
    public Kupon kuponsorgula(String kupon) {
        if (kupon == null){
            throw new IllegalArgumentException("Kupon yok");
        }
        Kupon kpn = kuponRepository.kuponsorgula(kupon);
        if (kpn==null){
            throw new IllegalArgumentException("Kupon Bulunamadı.");
        }
        if (kpn!=null && kpn.getAdet()==0){
            throw new IllegalArgumentException("Kupon Tükenmiştir");
        }
        kpn.setAdet(kpn.getAdet()-1);
        kuponRepository.save(kpn);
        return kpn;
    }
}
