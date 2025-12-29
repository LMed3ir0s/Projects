import salesRaw from '../data/sales.json';
import { toCents } from '../shared/utils/money-utils';
import { Sale } from '../modules/sales/domain/enitities/sale.entity';

interface SalesFileSchema {
  vendas: {
    vendedor: string;
    valor: number; // em R$
  }[];
}

const fileData = salesRaw as SalesFileSchema;

// Lista inicial de vendas normalizada
export const INITIAL_SALES: Sale[] =
  (fileData.vendas ?? []).map((item) => ({
    sellerName: item.vendedor,
    valueInCents: toCents(item.valor),
  }));
