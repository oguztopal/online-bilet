import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertifyService } from '../services/alertify.service';
import { BiletService } from '../services/bilet.service';
declare let alertify: any;
@Component({
  selector: 'app-biletbilgileri',
  templateUrl: './biletbilgileri.component.html',
  styleUrls: ['./biletbilgileri.component.css']
})
export class BiletbilgileriComponent implements OnInit {
  biletbilgileri:any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,private alertifyService:AlertifyService,private biletService:BiletService
  ) { }

  ngOnInit() {
    
    this.route.queryParams.subscribe((params)=>{
      debugger;
      this.biletbilgileri = JSON.parse(params.data);
      console.log(this.biletbilgileri);
    })
  }
 
  iptalEt(){
    alertify.confirm("İptal istediğinize emin misiniz?",(e, value) => {
      this.biletService.biletiptal(this.biletbilgileri.biletId).subscribe(response => {
        let temp:any= response;
        debugger;
        console.log(response);
      }, error => {
        debugger;
        if (error=="OK"){
          this.alertifyService.successtop("Biletiniz İptal Olmuştur.");
          return;
        }
        console.log(error);
        this.alertifyService.error(error);
      });  
    },
    function(){
      alertify.error('Cancel');
    }).setHeader('<em> Uyarı </em> ');;
  }

}
