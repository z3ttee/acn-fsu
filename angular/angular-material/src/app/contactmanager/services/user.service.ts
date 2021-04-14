import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/user';

@Injectable()
export class UserService {
  

  private _users: BehaviorSubject<User[]>

  private dataStore: {
    users: User[]
  }

  constructor(private http: HttpClient) { 
    this.dataStore = { users: [] }
    this._users = new BehaviorSubject<User[]>([]);
  }

  get users(): Observable<User[]> {
    return this._users.asObservable();
  }

  public loadAll() {
    const usersUrl = "https://angular-material-api.azurewebsites.net/users";

    return this.http.get<User[]>(usersUrl).subscribe(
      data => {
        this.dataStore.users = data;

        // Let datalistener know, that data is available
        this._users.next(Object.assign({}, this.dataStore).users);
      }, 
      error => console.log(error)
    );
  }

  public getById(id: number): User {
    return this.dataStore.users.find(user => {
      if(user.id == id) return user;
    })
  }

  public addUser(user: User): Promise<User> {
    return new Promise((resolve) => {
      user.id = this.dataStore.users.length + 1;
      this.dataStore.users.push(user)

      // Let datalistener know, that data is available
      this._users.next(Object.assign({}, this.dataStore).users);
      resolve(user);
    })
  }
}