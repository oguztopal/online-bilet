package com.oguztopal.onlinebilet.service;

import com.oguztopal.onlinebilet.entity.Havalimanlari;
import org.json.JSONObject;

import java.util.List;

public interface IHavalimanlariImpl {

    Havalimanlari havalimaniEkle(Havalimanlari havalimanlari);

    Boolean havalimaniSil(Long id);

    List<Havalimanlari> getButunHavalimanlari();

    Havalimanlari havalimaniGetir(Long id);

    JSONObject havalimanlari();
}
