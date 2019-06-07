import { Component, OnInit, ViewChild } from '@angular/core';
import { DxSelectBoxModule, DxDataGridComponent } from "devextreme-angular";
import { HavalimanlariService } from "../services/havalimanlari.service";
import { BiletService } from "../services/bilet.service";
import { Havalimanlari } from '../models/havalimanlari';
import { AlertifyService } from '../services/alertify.service';
import { DxFormModule } from "devextreme-angular";
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder
} from "@angular/forms";
import { UcusseferleriService } from '../services/ucusseferleri.service';
import { Ucusseferleri } from '../models/ucusseferleri';
import { Router, ActivatedRoute } from '@angular/router';
import { Bilet } from '../models/bilet';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-anasayfa',
  templateUrl: './anasayfa.component.html',
  styleUrls: ['./anasayfa.component.css']
})
export class AnasayfaComponent implements OnInit {
  selectBoxDataSource = [];
  kisiBilgileriForm: FormGroup;
  ucusBilgileri = {
    seferId: "",
    gidis: "",
    donus: "",
    kalkisTarihi: "",
    kalkis: "",
    varis: "",
    sirketler: "",
    biletFiyati: ""
  };
  biletBilgileri = {
    yolcuAdSoyad: "",
    yolcuTel: "",
    yolcuEmail: "",
    yolcuAdres: "",
    ucusseferleri: {
      seferId: ""
    }
  };
  aktifKullanici='';
  guncelBiletfiyati:number =0;




  constructor(private havalimanlariService: HavalimanlariService,
    private biletService: BiletService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route : ActivatedRoute,
    private ucusSeferleriService: UcusseferleriService,
    private alertifyService: AlertifyService) {
    this.cloneIconClick = this.cloneIconClick.bind(this);
  }
  @ViewChild("targetDataGrid") dataGrid: DxDataGridComponent;
  gridData = [];
  ucusBilgileriForms: FormGroup;
  dataSource: any[];
  ucusSeferlerii: FormGroup;
  ucusSeferleriList: Ucusseferleri[];
  popupVisible: boolean = false;
  veriler: any;
  submitted: boolean = false;
  kupon: boolean = false;
  ngOnInit() {
    this.havalimanlariGetir();
    this.createUcusSeferleri();
    
  }
  createUcusSeferleri() {
    this.ucusSeferlerii = this.formBuilder.group({
      gidis: ["", Validators.required],
      donus: ["", Validators.required],
      iptaldurumu: ["false"],
      kalkisTarihi: ["", [Validators.required]]
    });
  }

  createUcusForm() {
    this.kisiBilgileriForm = this.formBuilder.group({

      adsoyad: ["", Validators.required],
      telefon: ["", Validators.required],
      adres: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]]
    });
  }

  get f2() { return this.kisiBilgileriForm.controls; }

  buttonOptions: any = {
    text: "Kupon Kodu Sorgula",
    type: "success",
    useSubmitBehavior: true
}


onFormSubmit(e){
  let kupon = "";
  debugger;
  for(let i= 0 ; i<=8 ;i++){
    if(e.currentTarget[i].name=="kuponKodu"){
      kupon = e.currentTarget[i].value;
    }
  }
  if(this.kupon == true){
    this.alertifyService.successtop("Kupon Zaten Kullanılmıştır.");
    return;
  }
  this.ucusSeferleriService.kuponsorgula(kupon).subscribe(data => {
    this.kupon = true;
    let temp:number = 0 ;
    temp= +this.ucusBilgileri.biletFiyati;
   // temp= temp-data.indirim;
    this.ucusBilgileri.biletFiyati = ""+temp;
    this.guncelBiletfiyati =temp;
    this.alertifyService.successtop("İndirim Uygulanmıştır.");
  },error => {
    this.alertifyService.errortop(error);
  });
}

  havalimanlariGetir() {
    try {
      this.havalimanlariService.getAll().subscribe(
        data => {
          for (let i = 0; i < data.length; i++) {
            this.selectBoxDataSource.push(data);
          }

        },
        error => {
          this.alertifyService.error("hata veri gelmedi");
        }
      );
    } catch (e) {
      console.log(e);
    }
  }
  get f() { return this.ucusSeferlerii.controls; }

  ucusseferleri() {
    if(this.ucusSeferlerii.value.gidis==this.ucusSeferlerii.value.donus){
      this.alertifyService.errortop("İki Havalimani Aynı olamaz.");
      return ;
    }
    //this.ucusSeferleriList=Object.assign({}, this.ucusSeferlerii.value);
    this.gridData = [];
    this.ucusSeferleriService.ucusseferleriGetir("/ucuslar1", this.ucusSeferlerii.value).subscribe(data => {
      
      for (let i = 0; i < data.data.length; i++) {
        this.gridData.push(data.data[i])
      }
    },
      error => {
        this.alertifyService.success("Bu tarihte "+error);
      });
  }


  vazgec() {
    this.popupVisible = false;
  }
  cloneIconClick(e) {
    this.createUcusForm();
    this.popupVisible = true;
    debugger;
    this.ucusBilgileri.seferId = e.row.data.seferId;
    this.ucusBilgileri.gidis = e.row.data.gidis.havalimaniAdi;
    this.ucusBilgileri.donus = e.row.data.donus.havalimaniAdi;
    this.ucusBilgileri.kalkisTarihi = e.row.data.kalkisTarihi;
    this.ucusBilgileri.kalkis = e.row.data.kalkis;
    this.ucusBilgileri.varis = e.row.data.varis;
    this.ucusBilgileri.sirketler = e.row.data.sirketler.sirketAdi;
    this.ucusBilgileri.biletFiyati = e.row.data.biletFiyati;
    if(this.kupon==true){
      this.ucusBilgileri.biletFiyati = ""+this.guncelBiletfiyati;
    }
  }



  kisiBilgileri() {
    this.submitted = true;
    if (this.kisiBilgileriForm.valid) {
      this.biletBilgileri.ucusseferleri.seferId = this.ucusBilgileri.seferId;
      this.biletBilgileri.yolcuAdSoyad = this.kisiBilgileriForm.value.adsoyad;
      this.biletBilgileri.yolcuEmail = this.kisiBilgileriForm.value.email;
      this.biletBilgileri.yolcuAdres = this.kisiBilgileriForm.value.adres;
      this.biletBilgileri.yolcuTel = this.kisiBilgileriForm.value.telefon;

      this.biletService.post("/biletkayit", this.biletBilgileri).subscribe(
        data => {
          this.alertifyService.dialog("Sayın " + data.yolcuAdSoyad + " adına kesilen biletin PNR kodu : " + data.pnr + " olarak tanımlanmıştır.")
          this.alertifyService.success("Bilet satın alma işlemi başarıyla gerçekleşti.")
        }, error => {
          this.alertifyService.error("Bilet satın alma işleminde hata meydana geldi.")
        });
    } else {
      this.alertifyService.error("Lütfen tüm alanları doldurunuz.")
    }
  }


  // pnr.pnr = this.elements.body.lastElementChild.lastElementChild.value;

}
