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
import { EventRouteActivatorGuard } from './events/event-route-activator.guard';
import { EventListResolver } from './events/events-list-resolver.service';
import { UserModule } from './user/user.module';

const ROUTES: Routes = [

  // Using a resolver, when routing to that component, the resolver is called
  // and fetches the data. This data is then added as "events" property to the
  // routes "data" object.
  { path: "", component: EventListComponent, resolve: {
    events: EventListResolver
  }},

  { path: "event/new", component: CreateEventComponent, canDeactivate: ['canDeactiveCreateEvent'] },   // ORDER IS IMPORTANT, FIRST DECLARED GET FIRST PROCESSED
  { path: "event/:id", component: EventDetailsComponent, canActivate: [EventRouteActivatorGuard] },
  { path: "404", component: Error404Component },

  // Child routes for this route will be declared in the defined
  // module class at RouterModule.forChild
  { path: "user", loadChildren: () => UserModule },

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
    ToastrService,
    EventListResolver,
    {
      provide: "canDeactiveCreateEvent",
      useValue: checkDirtyState
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// The first parameter that gets passed is always the component, which gets deactivated on routing
export function checkDirtyState(component: CreateEventComponent) {
  if(component.isDirty) {
    return window.confirm("You have made unsaved changes. Are you sure you want to cancel?");
  }

  return true;
}

