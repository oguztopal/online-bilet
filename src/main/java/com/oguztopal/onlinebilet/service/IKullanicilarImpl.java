package com.oguztopal.onlinebilet.service;

import com.oguztopal.onlinebilet.entity.Kullanicilar;
import com.sun.org.apache.xpath.internal.operations.Bool;

public interface IKullanicilarImpl {

    Kullanicilar kullaniciGetirById(Long id);

    Kullanicilar kullaniciEkle(Kullanicilar kullanicilar);

    Boolean kullaniciSil(Long id);
}
