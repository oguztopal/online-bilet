package com.oguztopal.onlinebilet.service;

import com.oguztopal.onlinebilet.dto.UcusseferleriDto;
import com.oguztopal.onlinebilet.entity.Kupon;
import com.oguztopal.onlinebilet.entity.Ucusdurumlari;
import com.oguztopal.onlinebilet.entity.Ucusseferleri;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IUcusseferleriImpl {

    Ucusseferleri getUcusSeferi(Long id);

    List<UcusseferleriDto> getUcusSeferiByGidisAndDonus(UcusseferleriDto ucusseferleri);

    List<UcusseferleriDto> gidisveDonusUcusSeferleri(Long gidis,Long varis, Date kalkisTarihi,Boolean iptal) throws ParseException;

    Kupon kuponsorgula(String kupon);

    Boolean durumGuncelle(Ucusdurumlari ucusdurumlari, Ucusseferleri ucusseferleri);

    List<Ucusseferleri> butunUcusSeferleri();

    Ucusseferleri ucusseferleriguncelle(Ucusseferleri ucusseferleri) throws ParseException;

    Ucusseferleri ucusseferiekle(Ucusseferleri ucusseferleri) throws ParseException;

    Boolean ucusSeferiPasifYap(Long seferId);
}
