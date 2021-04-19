import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { ToastrService } from "../common/toastr.service";
import { EventService } from "./shared/event.service";

@Component({
    templateUrl: "./events-list.component.html",
    styleUrls: [ "./events-list.component.css" ]
})
export class EventListComponent implements OnInit {
  public events: any = [];
    
  constructor(
    private eventService: EventService, 
    private toastr: ToastrService,
    private route: ActivatedRoute
  ) { }

  public ngOnInit(): void {
    this.events = this.route.snapshot.data["events"];
  }

  public handleThumbnailClick(eventName: string): void {
    this.toastr.success(eventName);
  }

}