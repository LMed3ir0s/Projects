import { Body, Controller, Post } from '@nestjs/common';
import { ApiBody, ApiOkResponse, ApiOperation, ApiTags } from '@nestjs/swagger';
import { InventoryService } from '../services/inventory.service';
import { CreateInventoryMovementDto } from '../dtos/inventory-movement.dto';
import { InventoryStockDto } from '../dtos/iventory-stock.dto';
import { InventoryMovement } from '../domain/enitities/inventory-movement.entity';

@ApiTags('inventory')
@Controller('inventory')
export class InventoryController {
  constructor(private readonly inventoryService: InventoryService) {}

  @Post('movements')
  @ApiOperation({
    summary: 'Aplica uma movimentação de estoque (entrada ou saída) em um produto',
  })
  @ApiBody({ type: CreateInventoryMovementDto })
  @ApiOkResponse({ type: InventoryStockDto })
  async applyMovement(@Body() dto: CreateInventoryMovementDto): Promise<InventoryStockDto> {
    // Mapeia DTO -> entidade de domínio
    const movement: InventoryMovement = {
      movimentKey: dto.movimentKey,
      description: dto.description,
      productId: dto.productId,
      type: dto.type,
      quantityInMoviment: dto.quantityInMoviment,
    };

    // Chama service e retorna DTO de saída
    const result = await this.inventoryService.applyMovement(movement);
    return result;
  }
}
