export class User {
  constructor(id: number, firstName: string, lastName: string, email: string, gender: string, city: string, country: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.city = city;
    this.country = country;
  }

  id:number;
  firstName:string;
  lastName:string;
  email:string;
  gender:string;
  city:string;
  country:string;


}
