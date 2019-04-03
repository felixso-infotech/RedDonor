import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IContact } from 'app/shared/model/RedDonor/contact.model';
import { ContactService } from './contact.service';
import { IAddress } from 'app/shared/model/RedDonor/address.model';
import { AddressService } from 'app/entities/RedDonor/address';
import { IBloodGroup } from 'app/shared/model/RedDonor/blood-group.model';
import { BloodGroupService } from 'app/entities/RedDonor/blood-group';

@Component({
  selector: 'jhi-contact-update',
  templateUrl: './contact-update.component.html'
})
export class ContactUpdateComponent implements OnInit {
  contact: IContact;
  isSaving: boolean;

  addresses: IAddress[];

  contacts: IContact[];

  bloodgroups: IBloodGroup[];

  constructor(
    private jhiAlertService: JhiAlertService,
    private contactService: ContactService,
    private addressService: AddressService,
    private bloodGroupService: BloodGroupService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ contact }) => {
      this.contact = contact;
    });
    this.addressService.query({ filter: 'contact-is-null' }).subscribe(
      (res: HttpResponse<IAddress[]>) => {
        if (!this.contact.addressId) {
          this.addresses = res.body;
        } else {
          this.addressService.find(this.contact.addressId).subscribe(
            (subRes: HttpResponse<IAddress>) => {
              this.addresses = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.contactService.query().subscribe(
      (res: HttpResponse<IContact[]>) => {
        this.contacts = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.bloodGroupService.query().subscribe(
      (res: HttpResponse<IBloodGroup[]>) => {
        this.bloodgroups = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.contact.id !== undefined) {
      this.subscribeToSaveResponse(this.contactService.update(this.contact));
    } else {
      this.subscribeToSaveResponse(this.contactService.create(this.contact));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IContact>>) {
    result.subscribe((res: HttpResponse<IContact>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }

  private onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackAddressById(index: number, item: IAddress) {
    return item.id;
  }

  trackContactById(index: number, item: IContact) {
    return item.id;
  }

  trackBloodGroupById(index: number, item: IBloodGroup) {
    return item.id;
  }
}
