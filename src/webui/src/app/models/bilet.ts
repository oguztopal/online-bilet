import { Musteriler } from './musteriler';
import { Ucusseferleri } from './ucusseferleri';
import { Havalimanlari } from './havalimanlari';
export class Bilet {
    biletId: string;
    pNR: string;
    musteri_id: Musteriler;
    kalkisTarihi: Date;
    kalkisSaati: Date;
    ucusseferleri: Ucusseferleri;
    havalimanlari: Havalimanlari;
    donus: Havalimanlari;
    fiyat: number;
    tahminiSure: number;
    aktif: boolean;
}
