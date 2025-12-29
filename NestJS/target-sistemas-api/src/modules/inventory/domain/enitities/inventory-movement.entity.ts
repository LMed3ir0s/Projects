export type InventoryMovementType = 'IN' | 'OUT';

export class InventoryMovement {
    movimentKey: number;
    description: string;
    productId: number;
    type: InventoryMovementType;
    quantityInMoviment: number;
}