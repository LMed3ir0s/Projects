import { diffInDays } from "../../../../shared/utils/date-utils";
import { calculateInterest } from "../../../../shared/utils/interest-utils";
import { toCents, toReal } from "../../../../shared/utils/money-utils";

type InterestCalculationInput = ({
    dueAmount: number;
    overdueDate: Date;
    paymentDate?: Date; // default = hoje
});

export const calculateOverduePayment = ({
    dueAmount,
    overdueDate,
    paymentDate = new Date()
    }: InterestCalculationInput) => {
        const amountInCents = toCents(dueAmount);
        const daysLate = diffInDays(overdueDate, paymentDate);
        const interestInCents = Math.round(calculateInterest(amountInCents, daysLate));
        const totalInCents = amountInCents + interestInCents

        return {
            daysLate,
            amountInCents,
            interestInCents,
            totalInCents,
            dueAmount: toReal(amountInCents),
            interestInReais: toReal(interestInCents),
            totalInReais: toReal(totalInCents)
        };
    }