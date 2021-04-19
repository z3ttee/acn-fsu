import { Component } from "@angular/core";
import { AuthService } from "./auth.service";


@Component({
    templateUrl: "./login.component.html"
})
export class LoginComponent {

    public username: string = "";
    public password: string = "";

    constructor(private authService: AuthService){}

    public login(formValues: any): void {
        this.authService.loginUser(formValues.username, formValues.password)
    }

}