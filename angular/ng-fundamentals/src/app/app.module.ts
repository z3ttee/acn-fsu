import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EventListComponent } from './events/events-list.component';
import { EventThumbnailComponent } from './events/thumbnail/event-thumbnail.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { EventService } from './events/shared/event.service';
import { ToastrService } from './common/toastr.service';
import { RouterModule, Routes } from '@angular/router';
import { EventDetailsComponent } from './events/event-details/event-details.component';
import { CreateEventComponent } from './events/create-event/create-event.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Error404Component } from './errors/404.component';

const ROUTES: Routes = [
  { path: "", component: EventListComponent },
  { path: "event/new", component: CreateEventComponent },   // ORDER IS IMPORTANT, FIRST DECLARED GET FIRST PROCESSED
  { path: "event/:id", component: EventDetailsComponent },

  { path: "404", component: Error404Component },

  { path: "**", redirectTo: "/" }
]

@NgModule({
  declarations: [
    AppComponent,
    EventThumbnailComponent,
    NavigationComponent,
    EventDetailsComponent,
    EventListComponent,
    CreateEventComponent,
    Error404Component
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES),
    BrowserAnimationsModule
  ],
  providers: [
    EventService,
    ToastrService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }



