import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {

  public user: User;

  constructor(private route: ActivatedRoute, private userService: UserService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.user = null;

      let id = params["id"];
      if(!id) id = 1;

      this.userService.users.subscribe(users => {
        if (users.length == 0) return;

        setTimeout(() => {
          this.user = this.userService.getById(id);
        }, 1000)
      })
    })
  }

}
