import { EventEmitter } from '@angular/core';
import { Component, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NewContactDialogComponent } from '../../dialogs/new-contact/new-contact-dialog/new-contact-dialog.component';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

  @Output() toggleSidenav = new EventEmitter<void>();
  constructor(
    private dialog: MatDialog,
    private snackbar: MatSnackBar,
    private router: Router
  ) { }

  public ngOnInit(): void {
  }

  public openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackbar.open(message, action, {
      duration: 5000,
    });
  }

  public openAddContactDialog(): void {
    let dialogRef = this.dialog.open(NewContactDialogComponent, {
      width: "450px"
    })

    dialogRef.afterClosed().subscribe(result => {
      console.log("The dialog was closed", result);
      if(result) {
        this.openSnackBar("New contact created", "Navigate").onAction().subscribe(() => {
          // Navigate
          this.router.navigate(["/", result.id]);
        })
      }
    })
  }

}
