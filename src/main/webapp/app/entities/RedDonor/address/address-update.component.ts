import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAddress } from 'app/shared/model/RedDonor/address.model';
import { AddressService } from './address.service';
import { IContact } from 'app/shared/model/RedDonor/contact.model';
import { ContactService } from 'app/entities/RedDonor/contact';

@Component({
  selector: 'jhi-address-update',
  templateUrl: './address-update.component.html'
})
export class AddressUpdateComponent implements OnInit {
  address: IAddress;
  isSaving: boolean;

  contacts: IContact[];

  constructor(
    private jhiAlertService: JhiAlertService,
    private addressService: AddressService,
    private contactService: ContactService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ address }) => {
      this.address = address;
    });
    this.contactService.query().subscribe(
      (res: HttpResponse<IContact[]>) => {
        this.contacts = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.address.id !== undefined) {
      this.subscribeToSaveResponse(this.addressService.update(this.address));
    } else {
      this.subscribeToSaveResponse(this.addressService.create(this.address));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IAddress>>) {
    result.subscribe((res: HttpResponse<IAddress>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackContactById(index: number, item: IContact) {
    return item.id;
  }
}
