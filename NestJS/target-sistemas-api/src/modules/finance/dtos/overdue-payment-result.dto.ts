import { ApiProperty } from "@nestjs/swagger";
import { IsNumber, IsPositive } from "class-validator";

export class OverduePaymentResultDto {

    @ApiProperty({
        description: 'Quantidade de dias de atraso entre o vencimento e a data de pagamento',
        example: 5
    })
    @IsNumber()
    daysLate: number;

    @ApiProperty({
        description: 'Valor original da cobrança em R$ (sem juros)',
        example: 100.5,
    })
    @IsNumber()
    @IsPositive(({ message: 'Valor original da cobrança deve ser maior que zero.' }))
    amount: number;

    @ApiProperty({
        description: 'Valor do juros em R$',
        example: 12.5,
    })
    @IsNumber()
    @IsPositive(({ message: 'Valor do juros deve ser maior que zero.' }))
    interest: number;

    @ApiProperty({
        description: 'Valor total a pagar em R$ (valor original + juros)',
        example: 113.0,
    })
    @IsNumber()
    @IsPositive(({ message: 'Valor total a pagar deve ser maior que zero.' }))
    total: number;
}