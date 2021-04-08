import { Component, OnInit } from '@angular/core';
import { MAT_PROGRESS_SPINNER_DEFAULT_OPTIONS_FACTORY } from '@angular/material/progress-spinner';
import { EventService } from '../events/service/event.service';
import { Eventz } from '../events/model/eventz';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public events: Eventz[] = [];

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.getAllEvents();
  }

  public getAllEvents() {

    this.eventService.getAll().subscribe(
      (response: Eventz[]) => {
        this.events = response;
        console.log(this.events);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getOneEvent() {
    this.eventService.getById(24).subscribe(
      (response: Eventz) => {
        this.events.push(response);
        console.log(this.events);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchEvents(key: string): void {
    //si pas de recherche, on recup tous les vents
    if (key.trim().length == 0) {
      this.getAllEvents();
    } else {
      this.events = [];
      this.eventService.findByNameContains(key).subscribe(
        (response: Eventz[]) => {
          this.events = response;
          console.log("Found x events :"+this.events.length);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


}