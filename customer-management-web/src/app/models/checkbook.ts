import { Account } from "./account";
import { CheckbookStatusHist } from "./checkbook-status-hist";

export class Checkbook {
    id: number | undefined;
    number: string | undefined;
    checkQuantity: number | undefined;
    account: Account | undefined;
    currentStatus: CheckbookStatusHist | undefined;
}
