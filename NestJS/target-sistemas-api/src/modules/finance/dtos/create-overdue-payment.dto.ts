import { ApiProperty, ApiPropertyOptional } from "@nestjs/swagger";
import { IsNumber, IsOptional, IsPositive, IsString } from "class-validator";

export class CalculateOverduePaymentDto {
    @ApiProperty({
        description: 'Valor original da cobrança em R$ (sem juros)',
        example: 100.5
    })
    @IsNumber()
    @IsPositive(({ message: 'Valor original da cobrança deve ser maior que zero.' }))
    amount: number;

    @ApiProperty({
    description: 'Data de vencimento em formato ISO (yyyy-MM-dd ou ISO completa)',
    example: '2025-12-10'
    })
    @IsString()
    overdueDate: string;

    @ApiPropertyOptional({
        description: 'Data de pagamento em formato ISO; se não informado, considera a data de hoje',
        example: '2025-12-20',
    })
    @IsOptional()
    @IsString()
    paymentDate?: string;
}