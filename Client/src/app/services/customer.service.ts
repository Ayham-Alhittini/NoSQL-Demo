import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../model/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http : HttpClient) { }

  private baseUrl = "http://localhost:4000/api/customer";

  getCustomer(customerId: string) {
    return this.http.get<Customer>(this.baseUrl + "/" + customerId);
  }

}
