export interface IAddress {
  id?: number;
  location?: string;
  houseNumber?: number;
  city?: string;
  state?: string;
  zipCode?: number;
  contactId?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public location?: string,
    public houseNumber?: number,
    public city?: string,
    public state?: string,
    public zipCode?: number,
    public contactId?: number
  ) {}
}
