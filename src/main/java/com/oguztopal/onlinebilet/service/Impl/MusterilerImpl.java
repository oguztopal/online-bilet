package com.oguztopal.onlinebilet.service.Impl;

import com.oguztopal.onlinebilet.entity.Musteriler;
import com.oguztopal.onlinebilet.repository.MusterilerRepository;
import com.oguztopal.onlinebilet.service.IMusterilerImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;

@Service
public class MusterilerImpl implements IMusterilerImpl {
    private MusterilerRepository musterilerRepository;

    public MusterilerImpl(MusterilerRepository musterilerService) {
        this.musterilerRepository = musterilerService;
    }

    @Override
    public Musteriler musteriGetirById(Long id){
        return musterilerRepository.getMusterilerByMusteriId(id);
    }

    public Musteriler musteriEkle(Musteriler musteriler){
        if (musteriler == null){
            throw new IllegalArgumentException("Parametre Boş");
        }
        Musteriler musteriler1 = musterilerRepository.getMusterilerByMusteriId(musteriler.getMusteriId());
        if (musteriler1==null){
            Musteriler yeniMusteri = new Musteriler();
            yeniMusteri.setAd(musteriler.getAd());
            yeniMusteri.setSoyad(musteriler.getSoyad());
            yeniMusteri.setCinsiyet(musteriler.getCinsiyet());
            yeniMusteri.setDogumTarihi(musteriler.getDogumTarihi());
            yeniMusteri.setEmail(musteriler.getEmail());
            yeniMusteri.setSifre(musteriler.getSifre());
            yeniMusteri.setTel(musteriler.getTel());
            musterilerRepository.save(yeniMusteri);
            return yeniMusteri;
        }
        return  musterilerRepository.save(musteriler);
    }

    public Boolean musteriSil(Long id){
        Boolean check=false;
        if (id==null){
            throw new IllegalArgumentException("Id boş olamaz.!!");
        }
        Musteriler musteri = musterilerRepository.getMusterilerByMusteriId(id);
        if (musteri!=null){
            try{
                musterilerRepository.delete(musteri);
                check = true;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            throw new IllegalArgumentException("Böyle Bir Müşteri Bulunmamakta");
        }
        return check;
    }


}
