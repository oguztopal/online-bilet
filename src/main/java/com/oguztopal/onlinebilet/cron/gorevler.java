package com.oguztopal.onlinebilet.cron;

import com.oguztopal.onlinebilet.entity.Havalimanlari;
import com.oguztopal.onlinebilet.entity.Ucusdurumlari;
import com.oguztopal.onlinebilet.entity.Ucusseferleri;
import com.oguztopal.onlinebilet.service.Impl.HavalimanlariImpl;
import com.oguztopal.onlinebilet.service.Impl.UcusseferleriImpl;
import com.oguztopal.onlinebilet.util.VTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class gorevler {

    @Autowired
    private HavalimanlariImpl havalimanlari;
    @Autowired
    private UcusseferleriImpl ucusseferleri;


    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Scheduled(cron="0,20 * * * * *")
    public void ucusdurumucron () throws ParseException {
        Boolean check= false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        logger.info("> cron calisiyor");
        /*String date1 = "07/06/2019 18:00";*/

        List<Ucusseferleri> ucusseferleris = ucusseferleri.butunUcusSeferleri();

        for (Ucusseferleri ucus : ucusseferleris){
            String date = sdf.format(new Date());
            String ucustarih = sdf.format(ucus.getKalkisTarihi());
            //Date d3 = new DateAndTime().get
            Date d1 = sdf.parse(date);
            Date d2 = sdf.parse(ucustarih);
            /*Date d3 = sdf.parse(date1);*/
            long fark = d2.getTime() - d1.getTime();
            long dakikaFarki = TimeUnit.MILLISECONDS.toMinutes(fark);
            //long dakikaFarki = fark / (60 * 1000) % 60;
            int saat = d1.getHours();
            if ((dakikaFarki<=60 && dakikaFarki>30) && ucus.getDurum().equals(Ucusdurumlari.UCAK_KALKMADI)){
                check = ucusseferleri.durumGuncelle(Ucusdurumlari.GUVENLIK_KONTROLU,ucus);
                if (check==true){
                    logger.info("> ucus durumu güncellendi");
                }else{
                    logger.info("> ucus durumu güncellemesinde hata");
                }
            }if ((dakikaFarki<=30 && dakikaFarki>=15) && ucus.getDurum().equals(Ucusdurumlari.GUVENLIK_KONTROLU)){
                check = ucusseferleri.durumGuncelle(Ucusdurumlari.KALKIS_ICIN_HAZIR,ucus);
                if (check==true){
                    logger.info("> ucus durumu güncellendi");
                }else{
                    logger.info("> ucus durumu güncellemesinde hata");
                }
            }if (d2.compareTo(d1)>0 && (saat>ucus.getKalkis().getHours() &&  saat<ucus.getVaris().getHours()) && sdf1.format(d1) == sdf1.format(d2) && ucus.getDurum().equals(Ucusdurumlari.KALKIS_ICIN_HAZIR)){
                check = ucusseferleri.durumGuncelle(Ucusdurumlari.UCUS_GERCEKLESIYOR,ucus);
                if (check==true){
                    logger.info("> ucus durumu güncellendi");
                }else{
                    logger.info("> ucus durumu güncellemesinde hata");
                }
            }

        }

        List<Havalimanlari> list = havalimanlari.getButunHavalimanlari();
        logger.info("Toplam havalimani {}",list.size());

        logger.info("< cron bitti");
    }
}
