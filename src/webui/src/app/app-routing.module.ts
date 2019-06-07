import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnasayfaComponent } from './anasayfa/anasayfa.component';
import { LoginComponent } from './login/login.component';
import { UserEditComponent } from './kullaniciLogin/userEdit/userEdit.component';
import { RegisterComponent } from './register/register.component';
import { BiletbilgileriComponent } from './biletbilgileri/biletbilgileri.component';
import { HakkimizdaComponent } from './hakkimizda/hakkimizda.component';
import { UcusseferleriComponent } from './ucusseferleri/ucusseferleri.component';

const routes: Routes = [
  {path:"anasayfa", component:AnasayfaComponent},
  {path:"hakkimizda", component:HakkimizdaComponent},
  {path:"login", component:LoginComponent},
  {path:"misafir", component:UserEditComponent},
  {path:"register",component:RegisterComponent},
  {path:"ucusseferleri",component:UcusseferleriComponent},
  {path:"biletbilgileri",component:BiletbilgileriComponent},
  {path:"ucusseferleri",component:UcusseferleriComponent},
  { path: '**', redirectTo: 'anasayfa', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
