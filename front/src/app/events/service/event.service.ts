import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Eventz } from '../model/eventz';
import { map } from 'rxjs/operators';
import {  throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class EventService {

  private REST_API_SERVER = "http://localhost:8080/events";

  constructor(private http: HttpClient) { }

  getAll(): Observable<Eventz[]> {
    return this.http.get(this.REST_API_SERVER).pipe(
        map((result:any)=>{
          // remove _embedded from rest hateaos
        return result._embedded.event;
     })).pipe(catchError(this.handleError));
  }

  getById(id: number | string): Observable<Eventz> {
    return this.http.get<Eventz>(this.REST_API_SERVER + '/' + id).pipe(catchError(this.handleError));
  }

  findByNameContains(partOfname: string): Observable<Eventz[]> {
    return this.http.get<Eventz>(this.REST_API_SERVER + '/search/findByNameContainingIgnoreCase?name=' + partOfname).pipe(
      map((result:any)=>{
        // remove _embedded from rest hateaos
      return result._embedded.event;
   })).pipe(catchError(this.handleError));
  }

  create(event: Eventz): Observable<Eventz> {
    return this.http.post<Eventz>(this.REST_API_SERVER, event).pipe(catchError(this.handleError));
  }

  update(event: Eventz): Observable<Eventz> {
    return this.http.put<Eventz>(this.REST_API_SERVER + '/' + event.id, event).pipe(catchError(this.handleError));
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(this.REST_API_SERVER + '/' + id).pipe(catchError(this.handleError));
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}