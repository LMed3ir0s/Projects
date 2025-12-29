// src/test/unit/inventory/inventory.service.spec.ts
import { InventoryService } from '../../../modules/inventory/services/inventory.service';
import { InventoryMovement } from '../../../modules/inventory/domain/enitities/inventory-movement.entity';
import * as movementHelper from '../../../modules/inventory/domain/helpers/inventory-movement-applier';

// isola a função de domínio para testar só o service
jest.mock('../../../modules/inventory/domain/helpers/inventory-movement-applier');

describe('InventoryService', () => {
  let service: InventoryService;

  beforeEach(() => {
    service = new InventoryService();
  });

  it('deve lançar erro quando produto não existir para a movimentação', async () => {
    // Arrange
    const movement: InventoryMovement = {
      movimentKey: 1,
      description: 'Saída por venda',
      productId: 9999, // id que não existe no INITIAL_INVENTORY
      type: 'OUT',
      quantityInMoviment: 5,
    };

    // Act + Assert
    await expect(service.applyMovement(movement)).rejects.toBeInstanceOf(
    movementHelper.InventoryMovementError,
    );
  });

  it('deve delegar para applyInventoryMovement e retornar o estoque atualizado', async () => {
    // Arrange
    const movement: InventoryMovement = {
      movimentKey: 2,
      description: 'Entrada de reposição',
      productId: 101,
      type: 'IN',
      quantityInMoviment: 10,
    };

    // mock do helper de domínio para isolar o service
    const expectedNewStock = 999; // qualquer valor de teste

    (movementHelper.applyInventoryMovement as jest.Mock).mockReturnValueOnce(
      expectedNewStock,
    );

    // Act
    const result = await service.applyMovement(movement);

    // Assert
    // garante que o helper foi chamado com o produto encontrado e o movimento
    expect(movementHelper.applyInventoryMovement).toHaveBeenCalledTimes(1);
    const [calledProduct, calledMovement] = (movementHelper
      .applyInventoryMovement as jest.Mock).mock.calls[0];

    expect(calledMovement).toEqual(movement);
    expect(calledProduct.productId).toBe(movement.productId);

    // garante que o DTO de saída reflete o estoque retornado pelo helper
    expect(result.productId).toBe(movement.productId);
    expect(result.finalQuantityInStock).toBe(expectedNewStock);
  });
});
