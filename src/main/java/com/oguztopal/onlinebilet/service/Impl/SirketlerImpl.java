package com.oguztopal.onlinebilet.service.Impl;

import com.oguztopal.onlinebilet.entity.Sirketler;
import com.oguztopal.onlinebilet.repository.SirketlerRepository;
import com.oguztopal.onlinebilet.service.ISirketlerImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SirketlerImpl implements ISirketlerImpl {
    private final SirketlerRepository sirketlerRepository;

    public SirketlerImpl(SirketlerRepository sirketlerRepository) {
        this.sirketlerRepository = sirketlerRepository;
    }

    @Override
    public Sirketler sirketGetirById(Long id) {
        if (id==null){
            throw new IllegalArgumentException("Id Boş olamaz!!");
        }
        Sirketler sirket = sirketlerRepository.getOne(id);
        return sirket;
    }

    @Override
    public Sirketler sirketEkle(Sirketler sirketler) {
        Boolean sirketVarmi = false;
        Sirketler sirketler1 = sirketlerRepository.getBySirketId(sirketler.getSirketId());
        if (sirketler1 == null) {
            Sirketler yeniSirket = new Sirketler();
            yeniSirket.setSirketAdi(sirketler.getSirketAdi());
            sirketlerRepository.save(yeniSirket);
            return yeniSirket;
        }
        return sirketlerRepository.save(sirketler);
    }

    @Override
    public Boolean sirketSil(Long id) {
        Boolean check = false;
        if (id!=null){
            Sirketler sirket = sirketlerRepository.getBySirketId(id);
            try{
                 sirketlerRepository.delete(sirket);
                 check = true;
            }catch (Exception ex){
                ex.getMessage();
            }
        }else{
            throw new IllegalArgumentException("Boyle Bir Sirket Bulunmamaktadir!!");
        }
        return check;
    }

    @Override
    public List<Sirketler> getButunSirketler() {
       List<Sirketler> sirketlers = sirketlerRepository.getAllBySirketIdNotNull();
       return sirketlers;
    }
}
