import { IContact } from 'app/shared/model/RedDonor/contact.model';

export interface IContact {
  id?: number;
  name?: string;
  phoneNumber?: number;
  email?: string;
  age?: number;
  isEligible?: boolean;
  addressId?: number;
  contactId?: number;
  contacts?: IContact[];
  bloodGroupId?: number;
}

export class Contact implements IContact {
  constructor(
    public id?: number,
    public name?: string,
    public phoneNumber?: number,
    public email?: string,
    public age?: number,
    public isEligible?: boolean,
    public addressId?: number,
    public contactId?: number,
    public contacts?: IContact[],
    public bloodGroupId?: number
  ) {
    this.isEligible = this.isEligible || false;
  }
}
