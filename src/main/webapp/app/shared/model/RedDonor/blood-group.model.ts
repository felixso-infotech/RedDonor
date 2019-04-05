export interface IBloodGroup {
  id?: number;
  bloodGroup?: string;
}

export class BloodGroup implements IBloodGroup {
  constructor(public id?: number, public bloodGroup?: string) {}
}
