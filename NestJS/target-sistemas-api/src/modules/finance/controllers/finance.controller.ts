import { Body, Controller, Post } from "@nestjs/common";
import { ApiOperation, ApiResponse, ApiTags } from "@nestjs/swagger";
import { FinanceService } from "../services/finance.service";
import { OverduePaymentResultDto } from "../dtos/overdue-payment-result.dto";
import { CalculateOverduePaymentDto } from "../dtos/create-overdue-payment.dto";

@ApiTags('finance')
@Controller('finance')
export class FinanceController {
    constructor (private readonly financeService: FinanceService) {}

    @Post('overdue-payment')
    @ApiOperation({
        summary:  'Calcula juros de pagamento em atraso',
        description: 'Calcula o valor dos juros e o total a pagar a partir de um valor original e da data de vencimento.',
    })
    @ApiResponse({
        status: 201,
        description: 'Cálculo realizado com sucesso',
        type: OverduePaymentResultDto
    })
    calculateOverduePayment(@Body() dto: CalculateOverduePaymentDto): OverduePaymentResultDto {
        const result = this.financeService.calculateOverduePayment(dto);
        return result;
    }
}