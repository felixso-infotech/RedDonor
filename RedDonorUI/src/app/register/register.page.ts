import { BloodGroupService } from './../blood-group.service';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../register.service';
import { Storage } from '@ionic/storage';
import { Router } from '@angular/router';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  bloodGroups:any;
/*   register={
    displayName:"",
    phoneNumber:"",
    age:"",
    location:"",
    bloodGroupId:""

  }; */
  register:any={};

  constructor(private bloodGroupService: BloodGroupService, 
    private registerService:RegisterService, 
    private storage: Storage, private router:Router,
    private profileService: ProfileService) { }

  ngOnInit() {
    this.profileService.getProfile(this.registerService.phoneNumber).subscribe(data=>{this.register=data;})
    this.bloodGroupService.getBloodGroups().subscribe(data=>{this.bloodGroups=data;console.log(this.bloodGroups);});

  }
  logForm() {
    console.log(this.register);
    this.registerService.saveProfile(this.register);
    this.storage.set("phoneNumber",this.register.phoneNumber);
    this.router.navigate(['/tabs/home']);

  }

}
