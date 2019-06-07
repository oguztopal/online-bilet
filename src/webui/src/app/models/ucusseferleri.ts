import { Havalimanlari } from './havalimanlari';
import { Sirketler } from './sirketler';
import { Ucusdurumlari } from './ucusdurumlari';

export class Ucusseferleri {
      seferId:number
      gidis:Havalimanlari
      donus:Havalimanlari
      takvim:Date
      kalkisTarihi:Date
      aktif:boolean
      sirketler:Sirketler
      durum:Ucusdurumlari
      biletFiyati:number
      tahminiSure:number
}
