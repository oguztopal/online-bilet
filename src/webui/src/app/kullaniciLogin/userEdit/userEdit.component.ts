import { Component, OnInit } from "@angular/core";
import {  ActivatedRoute, Router } from "@angular/router";
import { KullaniciService } from "src/app/services/kullanici.service";
import { Kullanicilar } from "src/app/models/kullanicilar";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AlertifyService } from "src/app/services/alertify.service";

@Component({
  selector: "app-userEdit",
  templateUrl: "./userEdit.component.html",
  styleUrls: ["./userEdit.component.css"]
})
export class UserEditComponent implements OnInit {
  constructor(
    private activatedRoute: ActivatedRoute,
    private authService: KullaniciService,
    private formBuilder: FormBuilder,
    private router:Router,
    private alertifyService:AlertifyService
  ) {}

  kullanici: Kullanicilar;
  kullaniciForm: FormGroup;
  submitted = false;

  ngOnInit() {
    this.loadData();
    this.createKullaniciForm();
  }
  firmaListesineGit(){
    this.router.navigateByUrl("firma")
  }

  loadData() {
    var kullaniciId = this.activatedRoute.snapshot.params.kullaniciId;

    if (kullaniciId === localStorage.getItem("userId")) {
      this.authService.findKullanici(kullaniciId).subscribe(
        data => {
          this.kullaniciForm = this.formBuilder.group({
            kullaniciId: data.kullaniciId,
            adiSoyadi: data.adSoyad,
            kullaniciAdi: data.kullaniciAdi,
            email: data.email,
            sifre: data.sifre,
            adres: data.adres,
            telefon: data.telefon,
            durum: data.aktif
          });
        },
        error => {
          console.log(error);
        }
      );
    } else {
      this.alertifyService.error("Yanlış bir işlem yaptınız.");
    }
  }
  createKullaniciForm() {
    this.kullaniciForm = this.formBuilder.group({
      kullaniciId: ["", Validators.required],
      adiSoyadi: ["", Validators.required],
      kullaniciAdi: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      adres: ["", Validators.required],
      telefon: ["", Validators.required],
      durum: ["", Validators.required],
      sifre: ["",[Validators.required, Validators.minLength(5),Validators.maxLength(10)]]
      
    });
  }



 
  
  get f() { return this.kullaniciForm.controls; }

   editKullanici() { 
     this.submitted = true;
     if (this.kullaniciForm.valid) {
       this.kullanici = Object.assign({}, this.kullaniciForm.value);
       this.authService.editKullaniciLogin(this.kullanici);
     } else {
       this.alertifyService.error(
         "Kullanıcı güncelleme işlemi başarısız. Lütfen tüm alanları doğru bir şekilde doldurunuz."
       );
     }
   }
}
