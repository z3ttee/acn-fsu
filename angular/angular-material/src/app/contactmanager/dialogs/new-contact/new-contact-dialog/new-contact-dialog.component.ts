import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/contactmanager/models/user';
import { UserService } from 'src/app/contactmanager/services/user.service';

@Component({
  selector: 'app-new-contact-dialog',
  templateUrl: './new-contact-dialog.component.html',
  styleUrls: ['./new-contact-dialog.component.scss']
})
export class NewContactDialogComponent implements OnInit {

  public name = new FormControl('', [ Validators.required ]);
  public user: User;
  public avatars: string[] = [
    "svg-1", "svg-2", "svg-3", "svg-4"
  ]

  constructor(
    private dialogRef: MatDialogRef<NewContactDialogComponent>, 
    private userService: UserService) { }

  public ngOnInit(): void {
    this.user = new User();
  }

  public dismiss(): void {
    this.dialogRef.close(null);
  }

  public save(): void {
    if(this.name.invalid) return;

    this.user.name = this.name.value;

    this.userService.addUser(this.user).then(user => {
      this.dialogRef.close(user);
    })
  }

  public getErrorMessage(): string {
    if (this.name.hasError('required')) {
      return 'You must enter a value';
    }

    return "";
  }

}
