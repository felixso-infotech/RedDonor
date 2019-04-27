import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  url=environment.URL+"contacts/";

  constructor(private http: HttpClient) { }

  getProfile(number:string) {
    console.log(this.url+number);
    return this.http.get(this.url+number);
  }
}
