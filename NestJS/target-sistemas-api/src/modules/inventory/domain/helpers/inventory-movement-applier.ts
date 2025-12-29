import { InventoryMovement } from "../enitities/inventory-movement.entity";
import { InventoryProduct } from "../enitities/inventory-product.entity";

export class InventoryMovementError extends Error {
    constructor(message: string) {
        super(message);
        this.name = 'InventoryMovementError';
    }
}

export const applyInventoryMovement = (
    product: InventoryProduct,
    movement: InventoryMovement
): number => {
    if (movement.productId !== product.productId) {
        throw new InventoryMovementError('Movimentação não pertence ao produto informado (productId divergente).')
    }

    if(!movement.description || !movement.description.trim()) {
        throw new InventoryMovementError('Descrição da movimentação não pode ser vazia.');
    }

    if (movement.quantityInMoviment <= 0) {
        throw new InventoryMovementError('Quantidade de movimentação deve ser maior que zero.')
    }

    if (movement.type !== 'IN' && movement.type !== 'OUT') {
        throw new InventoryMovementError('Tipo de movimentação inválido. Use "IN" ou "OUT".')
    }

    const currentStock = product.quantityInStock ?? 0;

    // Entrada de estoque
    if (movement.type === 'IN') {
        return currentStock + movement.quantityInMoviment
    }

    // Valida Saída de estoque
    if (movement.type === 'OUT') {
        if (movement.quantityInMoviment > currentStock) {
        throw new InventoryMovementError(`Estoque insuficiente para saída. Estoque atual: ${currentStock},
             solicitado: ${movement.quantityInMoviment}.`);
        }
    }

    const newStock = currentStock - movement.quantityInMoviment;

    return newStock;
}