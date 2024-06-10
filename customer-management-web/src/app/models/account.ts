import { AccountStatusHist } from "./account-status-hist";
import { AccountType } from "./account-type";
import { Customer } from "./customer";

export class Account {
    id: number | undefined;
    number: string | undefined;
    openingAmount: number | undefined;
    balance: number | undefined;
    customer: Customer | undefined;
    accountType: AccountType | undefined;
    currentStatus: AccountStatusHist | undefined;
}
