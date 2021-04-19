import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ProfileComponent } from "./profile.component";

const USER_ROUTES: Routes = [
    { path: "profile", component: ProfileComponent }
]

@NgModule({
    imports: [
        // "Sub-Modules" need the CommonModule import
        CommonModule,
        RouterModule.forChild(USER_ROUTES)
    ],
    declarations: [
        ProfileComponent
    ],
    providers: [

    ]
})
export class UserModule {

}