import { Component, OnInit } from "@angular/core";
import { ToastrService } from "../common/toastr.service";
import { EventService } from "./shared/event.service";

@Component({
    templateUrl: "./events-list.component.html",
    styleUrls: [ "./events-list.component.css" ]
})
export class EventListComponent implements OnInit {
  public events: any[] = [];
    
  constructor(private eventService: EventService, private toastr: ToastrService) { 
    
  }

  public ngOnInit(): void {
    this.events = this.eventService.getEvents();
  }

  public handleThumbnailClick(eventName: string): void {
    this.toastr.success(eventName);
  }

}