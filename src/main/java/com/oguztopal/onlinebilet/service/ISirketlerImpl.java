package com.oguztopal.onlinebilet.service;

import com.oguztopal.onlinebilet.entity.Sirketler;

import java.util.List;

public interface ISirketlerImpl {
    Sirketler sirketGetirById(Long id);

    Sirketler sirketEkle(Sirketler sirketler);

    Boolean sirketSil(Long id);

    List<Sirketler> getButunSirketler();

}
