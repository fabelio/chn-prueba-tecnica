import { CheckStatusHist } from "./check-status-hist";
import { Checkbook } from "./checkbook";

export class Check {
    id: number | undefined;
    number: string | undefined;
    amount: number | undefined;
    checkbook: Checkbook | undefined;
    currentStatus: CheckStatusHist | undefined;
}
