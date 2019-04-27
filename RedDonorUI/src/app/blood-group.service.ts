import { environment } from './../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BloodGroupService {

  url=environment.URL+"blood-groups";

  constructor(private http: HttpClient) { }

  getBloodGroups() {
    return this.http.get(this.url);
  }
}
