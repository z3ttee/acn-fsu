import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login.component";
import { ProfileComponent } from "./profile.component";

const USER_ROUTES: Routes = [
    { path: "profile", component: ProfileComponent },
    { path: "login", component: LoginComponent }
]

@NgModule({
    imports: [
        // "Sub-Modules" need the CommonModule import
        CommonModule,
        FormsModule,
        RouterModule.forChild(USER_ROUTES)
    ],
    declarations: [
        ProfileComponent,
        LoginComponent
    ],
    providers: [

    ]
})
export class UserModule {

}