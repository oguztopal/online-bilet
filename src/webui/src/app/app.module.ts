import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnasayfaComponent } from './anasayfa/anasayfa.component';
import { KullaniciService } from './services/kullanici.service';

import { AlertifyService } from './services/alertify.service';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DevExtremeModule, DxSelectBoxModule, DxLookupModule, DxBoxModule, DxTreeListModule, DxFormModule, DxDataGridModule, DxButtonModule, DxPopupComponent, DxPopupModule } from 'devextreme-angular';
import {
  MatButtonModule,
  MatToolbarModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatTabsModule,
  MatCardModule,
  MatPaginatorModule,
  MatSortModule,
  MatFormFieldModule,
  MatGridListModule,
  MatInputModule,
  MatSnackBarModule,
  MatNativeDateModule,
  MatCheckboxModule,
  MatDatepickerModule,
  MatSelectModule,
  MatRadioModule,
  MatTooltipModule
} from "@angular/material";
import { MatTableModule } from "@angular/material/table";
import { LayoutModule } from '@angular/cdk/layout';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserEditComponent } from './kullaniciLogin/userEdit/userEdit.component';
import { RegisterComponent } from './register/register.component';
import { HavalimanlariService } from './services/havalimanlari.service';
import { UcusseferleriService } from './services/ucusseferleri.service';
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { BiletService } from './services/bilet.service';
import { BiletbilgileriComponent } from './biletbilgileri/biletbilgileri.component';
import { JwtInterceptor } from './security/jwt.interceptor';
import { AuthGuard } from './security/auth.guard';
import { AuthenticationService } from './security/authentication.service';
import { ErrorInterceptor } from './security/authentication.interceptor';
import { NavComponent } from './nav/nav.component';
import { HakkimizdaComponent } from './hakkimizda/hakkimizda.component';
import { UcusseferleriComponent } from './ucusseferleri/ucusseferleri.component';


@NgModule({
  declarations: [
    AppComponent,
    AnasayfaComponent,
    LoginComponent,
    UserEditComponent,
    RegisterComponent,
    BiletbilgileriComponent,
    NavComponent,
    HakkimizdaComponent,
    UcusseferleriComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatTabsModule,
    MatCardModule,
    MatTableModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatTooltipModule,
    MatGridListModule,
    MatInputModule,
    MatRadioModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatSnackBarModule,
    DevExtremeModule,
    DxSelectBoxModule,
    DxLookupModule,
    DxBoxModule,DxFormModule,DxDataGridModule,DxButtonModule,DxPopupModule,DxTreeListModule
  ],
  providers: [KullaniciService,AlertifyService,HavalimanlariService,UcusseferleriService,BiletService,AuthGuard,AuthenticationService,
    {provide: HTTP_INTERCEPTORS,useClass: JwtInterceptor,multi:true},
    {provide: HTTP_INTERCEPTORS,useClass: ErrorInterceptor,multi:true}],
  
  bootstrap: [AppComponent]
})
export class AppModule { }
platformBrowserDynamic().bootstrapModule(AppModule);
