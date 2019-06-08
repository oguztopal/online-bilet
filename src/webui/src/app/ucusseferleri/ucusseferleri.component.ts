import {Component, OnInit, ViewChild} from '@angular/core';
import { AlertifyService } from "../services/alertify.service";
import { DxDataGridComponent } from "devextreme-angular";

import {UcusseferleriService} from "../services/ucusseferleri.service";
import {HavalimanlariService} from "../services/havalimanlari.service";
import {Havalimanlari} from "../models/havalimanlari";

@Component({
  selector: 'app-ucusseferleri',
  templateUrl: './ucusseferleri.component.html',
  styleUrls: ['./ucusseferleri.component.css']
})
export class UcusseferleriComponent implements OnInit {

  constructor(private ucusSeferleriService:UcusseferleriService,private alertifyService:AlertifyService,private havalimanlariService:HavalimanlariService) { }
  @ViewChild("targetDataGrid") dataGrid: DxDataGridComponent;

  dataSource: any;
  seferDurumlari:any;
  sirketler: any;

  havalimanlari: Havalimanlari[];

  data: any;

  ngOnInit() {
    this.loadData();
    this.FindHavalimani();
    this.butunSirketler();
    this.seferDurumGetir();
  }
  FindHavalimani() {
    this.havalimanlariService.getAll().subscribe(
      data => {
        debugger;

        this.havalimanlari=data;
        console.log(this.havalimanlari)
      },
      error => {
        this.alertifyService.error(error);
      }
    );
  }
  onInitialized(e) {
    e.element.style.fontSize = "10px";
  }
  onRowInserting(e) {
    this.ucusSeferleriService.add(e.data).subscribe(
      data => {
        this.loadData();
        this.alertifyService.success(
          "Sefer ekleme işlemi başarıyla gerçekleşti."
        );
      },
      error => {
        this.alertifyService.error(error);
      }
    );
  }
  onRowUpdating(e) {
    this.data = Object.assign({}, e.oldData, e.newData);
    this.ucusSeferleriService.edit(this.data).subscribe(
      data => {
        this.loadData();
        this.alertifyService.success(
          "Sefer güncelleme işlemi başarıyla gerçekleşti."
        );
      },
      error => {
        this.loadData();
        this.alertifyService.error(error);
      }
    );
  }

  onRowRemoving(e) {
    this.ucusSeferleriService.delete(e.key).subscribe( //keyExpr ile id
      data => {
        this.loadData();
        this.alertifyService.warning("Silme işlemi başarıyla gerçekleşti.");
      },
      error => {
        this.loadData();
        this.alertifyService.error(error);
      }
    );
  }

  loadData() {
    this.ucusSeferleriService.getAll().subscribe(
      data => {
       this.dataSource=data;
      },
      error => {
        this.alertifyService.error(error);
      }
    );
  }
  butunSirketler(){
    debugger;
    this.ucusSeferleriService.getsirketler().subscribe(
      data => {
        this.sirketler=data;
      },
      error => {
        this.alertifyService.error(error);
      }
    );
  }
  seferDurumGetir(){
    this.seferDurumlari=this.ucusSeferleriService.getEnumSeferDurumlari();
  }
}
