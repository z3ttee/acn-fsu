import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  templateUrl: "./profile.component.html",
  styles: [`
    em { float:right; color: #e05c65; padding-left: 10px }
    .error input { background-color: #e3c3c5; }
    .error ::-webkit-input-placeholder { color: #999 }
    .error ::-moz-input-placeholder { color: #999 }
    .error :-moz-input-placeholder { color: #999 }
    .error :-ms-input-placeholder { color: #999 }
  `]
})
export class ProfileComponent implements OnInit {

  public profileForm!: FormGroup;
  private firstname!: FormControl;
  private lastname!: FormControl;

  constructor(private authService: AuthService, private router: Router){}

  public ngOnInit(): void {
    this.firstname = new FormControl(this.authService.currentUser?.firstname, [ Validators.required, Validators.pattern("[a-zA-Z].*") ]);
    this.lastname = new FormControl(this.authService.currentUser?.lastname, [ Validators.required ]);

    this.profileForm = new FormGroup({
      firstname: this.firstname,
      lastname: this.lastname
    });
  }

  public save(formValues: any): void {
    if(this.profileForm.valid) {
      this.authService.updateCurrentUser(formValues.firstname, formValues.lastname);
      this.router.navigate(["/"])
    }
  }

  public cancel(): void {
    this.router.navigate(["/"])
  }

  public validateLastname(): boolean {
    return this.lastname?.valid || this.lastname?.untouched
  }

  public validateFirstname(): boolean {
    return this.firstname?.valid || this.firstname?.untouched
  }

}