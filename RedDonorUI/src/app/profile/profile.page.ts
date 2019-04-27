import { ProfileService } from './../profile.service';
import { Component, OnInit } from '@angular/core';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  profile:any;

  constructor(private profileService:ProfileService, private storage:Storage) { }

  ngOnInit() {
    this.storage.get("phoneNumber").then(value=>
      {
        if(value!=null && value!=undefined){
          this.profileService.getProfile(value).subscribe(data=>{this.profile=data;console.log(this.profile);});
        }
        
          
      });
    
  }

}
