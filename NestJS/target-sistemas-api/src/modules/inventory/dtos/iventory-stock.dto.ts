import { ApiProperty } from '@nestjs/swagger';
import { IsInt, IsNotEmpty, IsString, Min} from 'class-validator'

export class InventoryStockDto {
  @ApiProperty({
    description: 'ID do produto movimentado',
    example: 101,
  })
  @IsInt({ message: 'productId deve ser um número inteiro.' })
  @Min(1, { message: 'productId deve ser maior ou igual a 1.' })
  productId: number;

  @ApiProperty({
    description: 'Nome do produto',
    example: 'Teclado Mecânico',
  })
  @IsString({ message: 'productName deve ser um texto.' })
  @IsNotEmpty({ message: 'productName não pode ser vazio.' })
  productName: string;

  @ApiProperty({
    description: 'Quantidade final em estoque após a movimentação',
    example: 42,
  })
  @IsInt({ message: 'finalQuantityInStock deve ser um número inteiro.' })
  @Min(0, { message: 'finalQuantityInStock deve ser zero ou maior.' })
  finalQuantityInStock: number;
}
