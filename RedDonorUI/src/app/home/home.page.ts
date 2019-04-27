import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { SocialSharing } from '@ionic-native/social-sharing/ngx';
import { Storage } from '@ionic/storage';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  constructor(private alertController: AlertController,private socialSharing: SocialSharing, private storage: Storage, private router: Router) { }

  ngOnInit() {
/*       if(!this.storage.get("phoneNumber")){
        console.log(this.storage.get("phoneNumber"));
        this.router.navigate(['/login']);
      } */
      this.storage.get("phoneNumber").then(value=>
                {
                  if(value==null || value==undefined)
                  this.router.navigate(['/verification']);
                    
      });
        
  }

  async presentAlertPrompt() {
    const alert = await this.alertController.create({
      header: 'Request blood',
      inputs: [
        {
          name: 'name',
          type: "text",
          placeholder: "Patient\'s name"
        },
        {
          name: 'bloodGroup',
          type: 'text',
          placeholder: 'blood group'
        },
        {
          name: 'unit',
          type: "number",
          placeholder: 'Unit of blood required'
        },
        // input date with min & max
        {
          name: 'bystanderName',
          type: 'text',
          placeholder:'Bystander\'s name'
        },
        // input date without min nor max
        {
          name: 'date',
          type: 'date',
          placeholder: "date",
          label:"date",
          min: Date.now().toString()
        },
        {
          name: 'hospitalDetails',
          type: 'text',
          placeholder: 'hospital and place'
        },
        {
          name: "contactNumber",
          type: "tel",
          placeholder: "contact number"
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'submit',
          cssClass: 'secondary',
          handler: () => {
            console.log('cancelled');
          }
        }, {
          text: 'Share',
          handler: (data) => {
            console.log('submitted');
            this.share(data);
          }
        }
      ]
    });

    await alert.present();
  }

  share(data) {
    let text=this.getTemplate(data);
    this.socialSharing.shareViaWhatsApp(text);
    console.log(text);
  }

  getTemplate(data): string {
    return "URGENTLY REQUIRED "+data.unit+" unit of "+data.bloodGroup+" blood!\n\n"+
    "patient: "+data.name+"\n"+
    "hospital & location:"+ data.hospitalDetails+"\n"+
    "Bystander: "+ data.bystanderName+"\n"+
    "Contact number: "+data.contactNumber+ "\n"+
    "Date: "+data.date+ "\n";
  }

}
