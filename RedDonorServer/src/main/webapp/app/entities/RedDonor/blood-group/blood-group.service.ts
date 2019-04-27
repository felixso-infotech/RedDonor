import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBloodGroup } from 'app/shared/model/RedDonor/blood-group.model';

type EntityResponseType = HttpResponse<IBloodGroup>;
type EntityArrayResponseType = HttpResponse<IBloodGroup[]>;

@Injectable({ providedIn: 'root' })
export class BloodGroupService {
  public resourceUrl = SERVER_API_URL + 'api/blood-groups';

  constructor(private http: HttpClient) {}

  create(bloodGroup: IBloodGroup): Observable<EntityResponseType> {
    return this.http.post<IBloodGroup>(this.resourceUrl, bloodGroup, { observe: 'response' });
  }

  update(bloodGroup: IBloodGroup): Observable<EntityResponseType> {
    return this.http.put<IBloodGroup>(this.resourceUrl, bloodGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBloodGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBloodGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
