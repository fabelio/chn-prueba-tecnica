import { Component } from '@angular/core';
import { Account } from '../../../models/account';
import { Checkbook } from '../../../models/checkbook';
import { AccountService } from '../../../services/account.service';
import { Router } from '@angular/router';
import { ChangeStatusDialogComponent } from '../../dialogs/change-status-dialog/change-status-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { CheckbookService } from '../../../services/checkbook.service';

@Component({
  selector: 'app-checkbooks',
  // standalone: true,
  // imports: [],
  templateUrl: './checkbooks.component.html',
  styleUrl: './checkbooks.component.scss'
})
export class CheckbooksComponent {
  account!: Account;
  checkbookList: Checkbook[] = [];
  constructor(private accountService: AccountService, private dialog: MatDialog, private route: Router, private checkbookServices: CheckbookService) {
    if (history.state != undefined)
      this.account = history.state;
  }

  ngOnInit(): void {
    this.getCheckbooks();
  }

  async getCheckbooks() {
    this.accountService.listAccountCheckbooks(this.account).subscribe(checkbooks => {
      this.checkbookList = checkbooks;
    }, error => {
      alert(error);
    });
  }

  createCheckbook() {
    this.route.navigate(['checkbook'], { state: this.account });
  }


  changeStatusCheckbookConfirmation(checkbook: Checkbook, statusCode: string, statusName: string) {
    this.dialog.open(ChangeStatusDialogComponent, {
      data: { message: `Desea actualizar estado de la chequera no. ${checkbook.number} a ${statusName}`, collect:false },
      width: '300px'
    }).afterClosed().subscribe((reason: string) => {
      console.log(reason)
      if (reason)
        this.checkbookServices.updateStatusCheckbook(checkbook.id!.toString(), statusCode, reason).subscribe(
          checkbook => {
            this.getCheckbooks();
          },
          error => {
            alert(error)
          });
    });
  }
}
