import { ContactData } from './../contact';
import { Component, OnInit } from '@angular/core';
import { Contacts, Contact, ContactFieldType, ContactName, IContactFindOptions } from '@ionic-native/contacts/ngx';
import { Storage } from '@ionic/storage';
import { LoadingController } from '@ionic/angular';
import { Router } from '@angular/router';

const options:IContactFindOptions= {
  filter:''
}

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.page.html',
  styleUrls: ['./contacts.page.scss'],
})
export class ContactsPage implements OnInit {

/*   fields:ContactFieldType[]=['displayName','phoneNumbers'];
  contactList= {
    phoneNumber:"",
    contactSets:[]
  }
  phoneContacts:any;

  constructor(private contacts: Contacts,private storage: Storage) { }

  ngOnInit() {
    this.storage.get("phoneNumber").then(value=>
      {
        if(value==null || value==undefined){
        this.contactList.phoneNumber=value;
        console.log(value);
        }

          
      });
      this.contacts.find(this.fields,options).then(data=>{this.phoneContacts=data;
        console.log(this.phoneContacts);});

        if(this.phoneContacts!=null && this.phoneContacts!=undefined){
          this.contactList.
        }

  } */
  constructor(public loadingController: LoadingController, private router:Router) {}
  ngOnInit() {
    this.presentLoadingWithOptions();
  }
  
  async presentLoadingWithOptions() {
    const loading = await this.loadingController.create({
      spinner: null,
      duration: 5000,
      message: 'Please wait...',
      translucent: true,
      cssClass: 'custom-class custom-loading'
    });
    this.router.navigate(['/tabs/home']);
    return await loading.present();
  }
}