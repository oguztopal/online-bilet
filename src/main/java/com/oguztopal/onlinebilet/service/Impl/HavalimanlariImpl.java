package com.oguztopal.onlinebilet.service.Impl;

import com.oguztopal.onlinebilet.entity.Havalimanlari;
import com.oguztopal.onlinebilet.repository.HavalimanlariRepository;
import com.oguztopal.onlinebilet.service.IHavalimanlariImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HavalimanlariImpl implements IHavalimanlariImpl {

    private final HavalimanlariRepository havalimanlariRepository;

    public HavalimanlariImpl(HavalimanlariRepository havalimanlariRepository) {
        this.havalimanlariRepository = havalimanlariRepository;
    }


    @Override
    public Havalimanlari havalimaniEkle(Havalimanlari havalimanlari) {
        if (havalimanlari == null){
            throw new IllegalArgumentException("Havalimani parametresi Boş olamaz");
        }
        Havalimanlari havalimanlari1 = havalimanlariRepository.getByHavalimaniId(havalimanlari.getHavalimaniId());
        if (havalimanlari1==null){
            Havalimanlari yeniHavalimani = new Havalimanlari();
            yeniHavalimani.setHavalimaniAdi(havalimanlari.getHavalimaniAdi());
            yeniHavalimani.setAdres(havalimanlari.getAdres());
            yeniHavalimani.setSehir(havalimanlari.getSehir());
            yeniHavalimani.setSehirKodu(havalimanlari.getSehirKodu());
            yeniHavalimani.setUlke(havalimanlari.getUlke());
            havalimanlariRepository.save(yeniHavalimani);
            return yeniHavalimani;
        }
        havalimanlariRepository.save(havalimanlari);
        return havalimanlari;
    }

    @Override
    public Boolean havalimaniSil(Long id) {
        Boolean check = false;
        if (id!=null){
            Havalimanlari havalimanlari = havalimanlariRepository.getByHavalimaniId(id);
            if (havalimanlari != null) {
                try {
                    havalimanlariRepository.delete(havalimanlari);
                    check = true;
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
        return check;
    }

    @Override
    public List<Havalimanlari> getButunHavalimanlari() {
        return havalimanlariRepository.getAllByHavalimaniIdIsNotNull();
    }

    @Override
    public Havalimanlari havalimaniGetir(Long id) {
        Havalimanlari havalimanlari = null;
        if (id!=null){
            Havalimanlari havalimanlari1 = havalimanlariRepository.getByHavalimaniId(id);
            if (havalimanlari1!=null){
                return havalimanlari1;
            }else{
                throw new IllegalArgumentException("Böyle Bir havalimani Bulunmamakta");
            }
        }
        return havalimanlari;
    }

    @Override
    public JSONObject havalimanlari() {
        List<Havalimanlari> havalimanlari = havalimanlariRepository.havalimanlariGetir();
        JSONObject json = new JSONObject();
        json.put("data",havalimanlari.get(0).getHavalimaniId());
        return json;
    }
}
