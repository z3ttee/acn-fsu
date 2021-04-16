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

const ROUTES: Routes = [
  { path: "", component: EventListComponent },
  { path: "event/:id", component: EventDetailsComponent },
  { path: "**", redirectTo: "/" }
]

@NgModule({
  declarations: [
    AppComponent,
    EventThumbnailComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [
    EventService,
    ToastrService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }



