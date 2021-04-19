import { Component, EventEmitter, Input, Output } from "@angular/core";
import { IEvent } from "src/app/shared";

@Component({
    selector: "event-thumbnail",
    templateUrl: "./event-thumbnail.component.html",
    styleUrls: [ "./event-thumbnail.component.css" ]
})
export class EventThumbnailComponent {

    @Input() event?: IEvent;

    public getStartTimeClass(): string {
        if(this.event && this.event.time == "8:00 am") {
            return "green bold";
        }

        return "";
    }

}