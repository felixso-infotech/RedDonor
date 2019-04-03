import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BloodGroup } from 'app/shared/model/RedDonor/blood-group.model';
import { BloodGroupService } from './blood-group.service';
import { BloodGroupComponent } from './blood-group.component';
import { BloodGroupDetailComponent } from './blood-group-detail.component';
import { BloodGroupUpdateComponent } from './blood-group-update.component';
import { BloodGroupDeletePopupComponent } from './blood-group-delete-dialog.component';
import { IBloodGroup } from 'app/shared/model/RedDonor/blood-group.model';

@Injectable({ providedIn: 'root' })
export class BloodGroupResolve implements Resolve<IBloodGroup> {
  constructor(private service: BloodGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BloodGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BloodGroup>) => response.ok),
        map((bloodGroup: HttpResponse<BloodGroup>) => bloodGroup.body)
      );
    }
    return of(new BloodGroup());
  }
}

export const bloodGroupRoute: Routes = [
  {
    path: 'blood-group',
    component: BloodGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'BloodGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'blood-group/:id/view',
    component: BloodGroupDetailComponent,
    resolve: {
      bloodGroup: BloodGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BloodGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'blood-group/new',
    component: BloodGroupUpdateComponent,
    resolve: {
      bloodGroup: BloodGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BloodGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'blood-group/:id/edit',
    component: BloodGroupUpdateComponent,
    resolve: {
      bloodGroup: BloodGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BloodGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bloodGroupPopupRoute: Routes = [
  {
    path: 'blood-group/:id/delete',
    component: BloodGroupDeletePopupComponent,
    resolve: {
      bloodGroup: BloodGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BloodGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
