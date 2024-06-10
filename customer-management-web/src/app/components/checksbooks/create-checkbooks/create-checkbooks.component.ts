import { Component } from '@angular/core';
import { Account } from '../../../models/account';
import { Checkbook } from '../../../models/checkbook';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CheckbookService } from '../../../services/checkbook.service';

@Component({
  selector: 'app-create-checkbooks',
  // standalone: true,
  // imports: [],
  templateUrl: './create-checkbooks.component.html',
  styleUrl: './create-checkbooks.component.scss'
})
export class CreateCheckbooksComponent {
  account!: Account;
  checkbook: Checkbook = new Checkbook();
  checkbookForm!: NgForm;
  isSubmitted: boolean = false;
  constructor(private router: Router, private checkbookService: CheckbookService) {
    if (history.state != undefined)
      this.account = history.state;
  }

  ngOnInit(): void {
  }

  createCheckbook(isValid: any) {
    this.isSubmitted = true;
    if (isValid) {

      this.checkbook.account = this.account;
      this.checkbookService.createCheckbook(this.checkbook).subscribe(
        checkbook => {
          if (checkbook != null) {
            this.router.navigate(['checkbooks'], { state: this.account });
          }
        },
        error => {
          alert(error)
        });
    }
  }
}
