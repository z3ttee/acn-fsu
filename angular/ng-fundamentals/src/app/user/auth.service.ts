import { Injectable } from "@angular/core";
import { IUser } from "./user.model";

@Injectable()
export class AuthService {

    public currentUser?: IUser;

    public loginUser(username: string, password: string) {
        this.currentUser = {
            id: 1,
            firstname: "Cedric",
            lastname: "Zitzmann",
            username
        }
    }

    public isAuthenticated() {
        return !!this.currentUser;
    }

}