import { IContact } from 'app/shared/model/RedDonor/contact.model';

export interface IContact {
  id?: number;
  displayName?: string;
  phoneNumber?: number;
  email?: string;
  age?: number;
  isEligible?: boolean;
  addressId?: number;
  bloodGroupId?: number;
  contactSets?: IContact[];
}

export class Contact implements IContact {
  constructor(
    public id?: number,
    public displayName?: string,
    public phoneNumber?: number,
    public email?: string,
    public age?: number,
    public isEligible?: boolean,
    public addressId?: number,
    public bloodGroupId?: number,
    public contactSets?: IContact[]
  ) {
    this.isEligible = this.isEligible || false;
  }
}
