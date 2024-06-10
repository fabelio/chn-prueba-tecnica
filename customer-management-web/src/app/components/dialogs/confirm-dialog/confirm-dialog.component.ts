import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrl: './confirm-dialog.component.scss'
})
export class ConfirmDialogComponent {

  message!: String;
  constructor(@Inject(MAT_DIALOG_DATA) public data: string, public dialogRef: MatDialogRef<ConfirmDialogComponent>) {
    this.message = data;
  }

  cerrarDialogo(): void {
    this.dialogRef.close(false);
  }
  confirmado(): void {
    this.dialogRef.close(true);
  }
}
