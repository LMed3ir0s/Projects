import { BadRequestException, Injectable } from "@nestjs/common";
import { CalculateOverduePaymentDto } from "../dtos/create-overdue-payment.dto";
import { OverduePaymentResultDto } from "../dtos/overdue-payment-result.dto";
import { OverduePayment } from "../domain/entities/overdue-payment.entity";

@Injectable()
export class FinanceService {
    calculateOverduePayment(dto: CalculateOverduePaymentDto): OverduePaymentResultDto {
        const { amount, overdueDate, paymentDate } = dto;
        const overdue = new Date(overdueDate);
        const payment = paymentDate ? new Date(paymentDate) : new Date();

        if (amount <= 0) {
            throw new BadRequestException('Valor da cobrança deve ser maior que zero');
        }

        if (isNaN(overdue.getTime())){
            throw new BadRequestException('Data de vencimento inválida');
        }

        if (paymentDate && isNaN(payment.getTime())) {
            throw new BadRequestException('Data de pagamento inválida');
        }

        const overduePayment = new OverduePayment({
            dueAmount: amount,
            overdueDate: overdue,
            paymentDate: payment
        });

        const valueObject = overduePayment.toValueObject();

        const result: OverduePaymentResultDto = {
            daysLate: valueObject.daysLate,
            amount: valueObject.AmountInReais,
            interest: valueObject.interestInReais,
            total: valueObject.totalInReais
        };

        return result;
    }
}