import { ApiProperty } from '@nestjs/swagger';
import type { InventoryMovementType } from '../domain/enitities/inventory-movement.entity';
import { IsEnum, IsInt, IsNotEmpty, IsPositive, IsString, Min} from 'class-validator'

export class CreateInventoryMovementDto {
    @ApiProperty({
        description: 'Identificador único da movimentação',
        example: 1,
    })
    @IsInt({ message: 'movimentKey deve ser um número inteiro.' })
    @Min(1, { message: 'movimentKey deve ser maior ou igual a 1.' })
    movimentKey: number;

    @ApiProperty({
        description: 'Descrição da movimentação (ex.: Entrada manual, Venda, Ajuste)',
        example: 'Saída por venda',
    })
    @IsString({ message: 'description deve ser um texto.' })
    @IsNotEmpty({ message: 'description não pode ser vazia.' })
    description: string;

    @ApiProperty({
        description: 'ID do produto a ser movimentado',
        example: 101,
    })
    @IsInt({ message: 'productId deve ser um número inteiro.'})
    @Min(1, { message: 'productId deve ser maior ou igual a 1.'})
    productId: number;

    @ApiProperty({
        description: 'Tipo da movimentação: IN (entrada) ou OUT (saída)',
        example: 'OUT',
        enum: ['IN', 'OUT']
    })
    @IsEnum(['IN', 'OUT'], {
        message: 'type deve ser "IN" ou "OUT".'
    })
    type: InventoryMovementType;

    @ApiProperty({
        description: 'Quantidade movimentada (sempre positiva, em unidades)',
        example: 5,
    })
    @IsInt({ message: 'quantityInMoviment deve ser um número inteiro.' })
    @IsPositive({ message: 'quantityInMoviment deve ser maior que zero.' })
    quantityInMoviment: number;
}
