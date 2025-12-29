import { diffInDays } from '../../../../shared/utils/date-utils';
import { calculateInterest } from "../../../../shared/utils/interest-utils";
import { toCents, toReal } from "../../../../shared/utils/money-utils";

type OverduePaymentProps = {
    dueAmount: number; // em R$
    overdueDate: Date; // dada de vencimento
    paymentDate?: Date; // default hoje
};

export class OverduePayment {
    private readonly amountInCents: number;
    private readonly overdueDate: Date;
    private readonly paymentDate: Date;

    constructor({ dueAmount, overdueDate, paymentDate } : OverduePaymentProps ) {
        const amountInCents = toCents(dueAmount);
        this.amountInCents = amountInCents;
        this.overdueDate = overdueDate;
        this.paymentDate = paymentDate ?? new Date();
    }

    getDaysLate(): number{
        const daysLate = diffInDays(this.overdueDate, this.paymentDate);
        return daysLate;
    }

    getInteresetInCents(): number {
        const daysLate = this.getDaysLate();
        if(daysLate <= 0) return 0;

        const interestInCents = Math.round(calculateInterest(this.amountInCents, daysLate));
        
        return interestInCents;
    }
    
    getTotalInCents(): number {
        const totalInCents = this.amountInCents + this.getInteresetInCents();
        return totalInCents;
    }

    
    getAmountInReais(): number{
        const AmountInReais = toReal(this.amountInCents);
        return AmountInReais;
    }
    
    getInterestInReais(): number {
        const interestInCents = this.getInteresetInCents();
        const interestInReais = toReal(interestInCents);
        return interestInReais
    }
    
    getTotalInReais(): number {
        const totalInCents = this.getTotalInCents();
        const totalInReais = toReal(totalInCents);
        return totalInReais;
    }

    toValueObject() {
        const daysLate = this.getDaysLate();
        const amountInCents = this.amountInCents;
        const interestInCents = this.getInteresetInCents();
        const totalInCents = this.getTotalInCents();
        const AmountInReais = this.getAmountInReais();
        const interestInReais = this.getInterestInReais();
        const totalInReais = this.getTotalInReais();

        return {
            daysLate,
            amountInCents,
            interestInCents,
            totalInCents,
            AmountInReais,
            interestInReais,
            totalInReais            
        };
    }
}