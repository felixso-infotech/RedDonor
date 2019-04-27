import { environment } from './../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  phoneNumber:any;
  url=environment.URL+"contacts/createContact";

  constructor(private http: HttpClient) { }

  saveProfile(data) {
    console.log(data);
    this.http.post(this.url,data).subscribe(data=>{console.log("posted data");});
  }
}
