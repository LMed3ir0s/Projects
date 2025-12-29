import inventoryRaw from '../data/inventory.json';

// Estrutura do arquivo JSON bruto.
interface inventoryFileSchema {
    estoque: {
        codigoProduto: number;
		descricaoProduto: string;
		estoque: number;
    } [];
}

// Modelo de produto em estoque.
export interface ProductConfig {
    codeProduct: number;
    description: string;
    stock: number;
}

const fileData = inventoryRaw as inventoryFileSchema;

// Estoque inicial em memória, mapeado para o modelo interno.
export const INITIAL_INVENTORY: ProductConfig[] =
    (fileData.estoque ?? []).map((item) => ({
            codeProduct: item.codigoProduto,
            description: item.descricaoProduto,
            stock: item.estoque
        })
    );