package com.oguztopal.onlinebilet.service;

import com.oguztopal.onlinebilet.entity.Musteriler;

public interface IMusterilerImpl {
    Musteriler musteriGetirById(Long id);

    Boolean musteriSil(Long id);

    Musteriler musteriEkle(Musteriler musteriler);
}
