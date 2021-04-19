import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "./auth.service";


@Component({
    templateUrl: "./login.component.html",
    styles: [`
        em {
            float: right;
            padding-left: 10px;
            color: #e05c65;
        }
    `]
})
export class LoginComponent {

    public username: string = "";
    public password: string = "";

    constructor(private authService: AuthService, private router: Router){}

    public login(formValues: any): void {
        this.authService.loginUser(formValues.username, formValues.password)
        this.router.navigate(["/"])
    }

    public cancel(): void {
        this.router.navigate(["/"])
    }

}