import { ApiProperty } from "@nestjs/swagger";
import { IsNotEmpty, IsNumber, IsString, Min } from "class-validator";

export class SaleRequestDto {
  @ApiProperty({description: 'Nome do vendedor', example: 'João da Silva'})
  @IsString({ message: 'sellerName deve ser um texto.' })
  @IsNotEmpty({ message: 'sellerName não pode ser vazio.' })
  sellerName: string;

  @ApiProperty({description: 'Valor da venda em R$', example: '756.73'})
  @IsNumber({}, { message: 'value deve ser um número.' })
  @Min(0.01, { message: 'value deve ser  maior que zero.' })
  value: number;
}

export class CalculateCommissionRequestDto {
  @ApiProperty({description: 'Lista de vendas para cálculo de comissão', type: [SaleRequestDto]})
  sales: SaleRequestDto[];
}
