// src/test/unit/inventory/inventory.controller.spec.ts
import { InventoryController } from '../../../modules/inventory/controllers/inventory.controller';
import { InventoryService } from '../../../modules/inventory/services/inventory.service';
import { CreateInventoryMovementDto } from '../../../modules/inventory/dtos/inventory-movement.dto';
import { InventoryStockDto } from '../../../modules/inventory/dtos/iventory-stock.dto';
import { InventoryMovement } from '../../../modules/inventory/domain/enitities/inventory-movement.entity';

describe('InventoryController', () => {
  let controller: InventoryController;
  let inventoryService: jest.Mocked<InventoryService>;

  beforeEach(() => {
    inventoryService = {
      applyMovement: jest.fn(),
    } as any as jest.Mocked<InventoryService>;

    controller = new InventoryController(inventoryService);
  });

  it('deve mapear o DTO para InventoryMovement e delegar ao service', async () => {
    // Arrange
    const dto: CreateInventoryMovementDto = {
      movimentKey: 1,
      description: 'Entrada inicial de estoque',
      productId: 1001,
      type: 'IN',
      quantityInMoviment: 10,
    };

    const expectedServiceResult: InventoryStockDto = {
      productId: 1001,
      productName: 'Produto A',
      finalQuantityInStock: 20,
    };

    inventoryService.applyMovement.mockResolvedValueOnce(expectedServiceResult);

    // Act
    const result = await controller.applyMovement(dto);

    // Assert
    expect(inventoryService.applyMovement).toHaveBeenCalledTimes(1);

    const movementArg: InventoryMovement =
      inventoryService.applyMovement.mock.calls[0][0];

    expect(movementArg).toEqual<InventoryMovement>({
      movimentKey: dto.movimentKey,
      description: dto.description,
      productId: dto.productId,
      type: dto.type,
      quantityInMoviment: dto.quantityInMoviment,
    });

    expect(result).toBe(expectedServiceResult);
  });
});
