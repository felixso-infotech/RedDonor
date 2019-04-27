import { environment } from './../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

  url=environment.URL+"contactsOfContacts/";

  constructor(private http: HttpClient) { }

  getContacts(number:string) {
    console.log(this.url+number);
    return this.http.get(this.url+number);
  }
}
