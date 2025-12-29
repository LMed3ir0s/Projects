import { Injectable, Logger } from "@nestjs/common";
import { InventoryProduct } from "../domain/enitities/inventory-product.entity";
import { INITIAL_INVENTORY, ProductConfig } from "../../../config/inventory.config";
import { InventoryMovement } from "../domain/enitities/inventory-movement.entity";
import { InventoryStockDto } from "../dtos/iventory-stock.dto";
import { applyInventoryMovement, InventoryMovementError } from "../domain/helpers/inventory-movement-applier";

@Injectable()
export class InventoryService {
    private readonly logger = new Logger (InventoryService.name);

    // “DB” em memória baseado no JSON
    private inventoryDB: InventoryProduct[] = INITIAL_INVENTORY.map(
        (item: ProductConfig): InventoryProduct => ({
            productId: item.codeProduct,
            productName: item.description,
            quantityInStock: item.stock
        })
    );

    async applyMovement(movement: InventoryMovement): Promise<InventoryStockDto> {
        if (!movement) {
            throw new InventoryMovementError('Movimentação inválida.');
        }

        const product = this.inventoryDB.find(
            (p) => p.productId === movement.productId
        );

        if (!product) {
            this.logger.warn(
                `Produto ${movement.productId} não encontrado para movimentação ${movement.movimentKey}`
            );
            throw new InventoryMovementError('Produto inválido para movimentação de estoque.');
        }

        this.logger.log(
            `Aplicando movimentação ${movement.movimentKey} (${movement.type}) para produto ${product.productId} - ${product.productName}`
        );

        const newStock = applyInventoryMovement(product, movement);

        // atualiza o “banco” em memória
        product.quantityInStock = newStock;

        this.logger.log(
            `Estoque atualizado para o produto ${product.productId} - ${product.productName}: ${newStock}`
        );

        // DTO de saída
        const result: InventoryStockDto = {
            productId: product.productId,
            productName: product.productName,
            finalQuantityInStock: product.quantityInStock
        };

        return result;
    }
}
