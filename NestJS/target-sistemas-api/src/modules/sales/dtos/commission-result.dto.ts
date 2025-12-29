import { ApiProperty } from "@nestjs/swagger";
import { IsNotEmpty, IsNumber, IsString, Min } from "class-validator";

export class CommissionResultDto {
    @ApiProperty({description: 'Nome do vendedor', example: 'João da Silva'})
    @IsString({ message: 'sellerName deve ser um texto.' })
    @IsNotEmpty({ message: 'sellerName não pode ser vazio.' })
    sellerName: string;

    @ApiProperty({description: 'Total de vendas em R$', example: '1500.75'})
    @IsNumber({}, { message: 'totalSales deve ser um número inteiro.' })
    @Min(1, { message: 'totalSales deve ser maior ou igual a 1.' })
    totalSales: number;

    @ApiProperty({description: 'Total de comissão em R$', example: '75.04'})
    @IsNumber({}, { message: 'totalCommission deve ser um número inteiro.' })
    @Min(1, { message: 'totalCommission deve ser maior ou igual a 1.' })
    totalCommission: number;
}
