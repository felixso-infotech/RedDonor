import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.page.html',
  styleUrls: ['./verification.page.scss'],
})
export class VerificationPage implements OnInit {

  constructor(private registerService:RegisterService,private router:Router) { }

  ngOnInit() {
    (<any>window).AccountKitPlugin.loginWithPhoneNumber({	useAccessToken: true,
      defaultCountryCode: "IN",
      facebookNotificationsEnabled: true},
      data=>{
        console.log("verification success");
        console.log(data);
        (<any>window).AccountKitPlugin.getAccount(
          info=>{
            console.log(info.phoneNumber);
            this.registerService.phoneNumber=info.phoneNumber;
            this.router.navigate(['/register']);
          },
          err=>{console.log(err);})
        }
      );
  }

}
