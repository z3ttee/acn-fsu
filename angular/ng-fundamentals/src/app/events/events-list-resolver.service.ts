import { Injectable } from "@angular/core";
import { Resolve } from "@angular/router";
import { EventService } from "../shared/event.service";
import { map } from "rxjs/operators";

@Injectable()
export class EventListResolver implements Resolve<any> {

    constructor(private eventService: EventService) { }

    public resolve() {
        // Pipe and map are being used to return an observable.
        // If subscribe() would be used, a Subscription will be returned, but
        // not an Observable      
        
        return this.eventService.getEvents().pipe(map(events => {
            return events;
        }))
    }


}