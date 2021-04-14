import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/contactmanager/models/user';

@Component({
  selector: 'app-new-contact-dialog',
  templateUrl: './new-contact-dialog.component.html',
  styleUrls: ['./new-contact-dialog.component.scss']
})
export class NewContactDialogComponent implements OnInit {

  public user: User;
  public avatars: string[] = [
    "svg-1", "svg-2", "svg-3", "svg-4"
  ]

  constructor(private dialogRef: MatDialogRef<NewContactDialogComponent>) { }

  public ngOnInit(): void {
    this.user = new User();
  }

  public dismiss(): void {
    this.dialogRef.close(null);
  }

  public save(): void {
    this.dialogRef.close(this.user);
  }

}
