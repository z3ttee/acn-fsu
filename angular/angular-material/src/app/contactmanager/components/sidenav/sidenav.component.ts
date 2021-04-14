import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDrawer, MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';

const CUSTOM_SMALL_WIDTH_BREAKPOINT = 720;

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  public isScreenSmall: boolean;
  public users: Observable<User[]>;

  @ViewChild(MatDrawer) drawer: MatDrawer;

  constructor(private breakpointObserver: BreakpointObserver, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    //this.breakpointObserver.observe([ Breakpoints.XSmall ])
    this.breakpointObserver.observe([ `(max-width: ${CUSTOM_SMALL_WIDTH_BREAKPOINT}px)` ]).subscribe((state: BreakpointState) => {
      this.isScreenSmall = state.matches;
    })

    this.users = this.userService.users;
    this.userService.loadAll();

    this.router.events.subscribe(() => {
      if(this.isScreenSmall) {
        this.drawer.close();
      }
    });
  }

}
