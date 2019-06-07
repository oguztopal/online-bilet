package com.oguztopal.onlinebilet.service;

import com.oguztopal.onlinebilet.entity.Bilet;
import com.oguztopal.onlinebilet.entity.Ucusseferleri;

public interface IBiletImpl {
    Bilet getBiletId(Long id);

    Bilet biletOlustur(Bilet bilet);

    Bilet pnrSorgulama(String pnr);

    String biletIptal(Long biletId);
}
