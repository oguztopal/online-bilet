import { Injectable } from "@angular/core";
declare let alertify: any;
@Injectable({
  providedIn: "root"
})
export class AlertifyService {
  constructor() {}

  success(message: string) {
    alertify.success(message);
  }
  warning(message: string) {
    alertify.warning(message);
  }
  error(message: string) {
    alertify.error(message);
  }
  errortop(message: string) {
    alertify.error(message);
    alertify.set('notifier','position', 'top-center');
  }
  successtop(message: string) {
    alertify.success(message);
    alertify.set('notifier','position', 'top-center');
  }

dialog(message:string){
  alertify.alert(message, function(){ }).setHeader('<em> Bilgi </em> '); 
}




}
