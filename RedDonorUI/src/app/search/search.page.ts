import { SocialSharing } from '@ionic-native/social-sharing/ngx';
import { ContactsService } from './../contacts.service';
import { BloodGroupService } from './../blood-group.service';
import { Component, OnInit } from '@angular/core';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-search',
  templateUrl: './search.page.html',
  styleUrls: ['./search.page.scss'],
})
export class SearchPage implements OnInit {

  bloodGroups: any;
  contacts: any;
  filteredContacts: any;

  constructor(private bloodGroupService: BloodGroupService, private contactService: ContactsService, private socialSharing: SocialSharing, private storage:Storage) { }

  ngOnInit() {
    this.bloodGroupService.getBloodGroups().subscribe(data => { this.bloodGroups = data; console.log(this.bloodGroups); });
    

      this.storage.get("phoneNumber").then(value=>
        {
          if(value!=null && value!=undefined){
            this.contactService.getContacts(value)
            .subscribe(data => { this.contacts = data; this.filteredContacts = data; console.log(this.contacts); });
          }
          
            
        });
  }

  filterByBloodGroup(event) {
    this.filteredContacts = this.contacts.filter(data => (data.bloodGroup.id == event.target.value));
  }

  shareContacts() {
    let contactList = "Please share the blood donor's list\n\n";
    /*     this.filteredContacts.forEach(element => {
          contactList+element.displayName+", "+element.bloodGroup.bloodGroup+", "+element.phoneNumber+"\n";
          console.log(contactList);
        }); */
    console.log(this.contacts);
    if (this.filteredContacts) {
      for (let element of this.filteredContacts) {
        contactList = contactList + element.displayName + ", " + element.bloodGroup.bloodGroup + ", " + element.phoneNumber + "\n";
        //console.log(element);
      }
    }
    //console.log(contactList);
    this.socialSharing.shareViaWhatsApp(contactList);
  }
}
