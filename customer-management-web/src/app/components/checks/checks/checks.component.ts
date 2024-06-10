import { Component } from '@angular/core';
import { Checkbook } from '../../../models/checkbook';
import { Check } from '../../../models/check';
import { CheckbookService } from '../../../services/checkbook.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ChangeStatusDialogComponent } from '../../dialogs/change-status-dialog/change-status-dialog.component';
import { CheckService } from '../../../services/check.service';
import { AccountService } from '../../../services/account.service';

@Component({
  selector: 'app-checks',
  // standalone: true,
  // imports: [],
  templateUrl: './checks.component.html',
  styleUrl: './checks.component.scss'
})
export class ChecksComponent {
  checkbook!: Checkbook;
  checkList: Check[] = [];
  constructor(private checkbookService: CheckbookService, private route: Router, private dialog: MatDialog,
    private checkService: CheckService, private accountService: AccountService) {
    if (history.state != undefined)
      this.checkbook = history.state;
  }

  ngOnInit(): void {
    this.getChecks();
  }

  async getChecks() {
    this.checkbookService.listCheckCheckbook(this.checkbook).subscribe(checks => {
      this.checkList = checks;
    }, error => {
      console.error(error);
    });
  }

  changeStatusCheckConfirmation(check: Check, statusCode: string, statusName: string) {
    this.dialog.open(ChangeStatusDialogComponent, {
      data: { message: `Desea actualizar estado cheque no. ${check.number} a ${statusName}` },
      width: '300px'
    }).afterClosed().subscribe((reason: string) => {
      if (reason)
        this.checkService.updateStatusCheck(check.id!.toString(), statusCode, reason).subscribe(
          check => {
            this.getChecks();
          },
          error => {
            alert(error)
          });
    });
  }

  collectCheck(check: Check) {
    this.dialog.open(ChangeStatusDialogComponent, {
      data: { message: `Ingrese el monto del cheque`, collect: true },
      width: '300px'
    }).afterClosed().subscribe((amount: number) => {
      if (amount)
        this.accountService.collectCheckAccount(check.checkbook!.account!.id!.toString(), check.checkbook!.id!.toString(), check.id!.toString(), amount).subscribe(
          account => {
            this.getChecks();
          },
          error => {
            console.log(error);
            alert(error)
          }
        );
    });
  }
}
