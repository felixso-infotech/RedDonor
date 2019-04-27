export class ContactData {
    public displayName:string="";
    public phoneNumber:string="";
    constructor(name:string, number:string) {
        this.displayName=name;
        this.phoneNumber=number;
    }
}