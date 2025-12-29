import { Injectable, Logger } from "@nestjs/common";
import { CommissionBySeller } from "../domain/enitities/commission-seller.entity";
import { INITIAL_SALES } from '../../../config/sales.config';
import { calculateComissionForSales } from "../domain/helpers/commission-calculator";
import { Sale } from "../domain/enitities/sale.entity";

@Injectable()
export class SalesService {
    private readonly logger = new Logger(SalesService.name);

    async calculateCommissionFromJson(): Promise<CommissionBySeller[]> {
        if (!INITIAL_SALES || INITIAL_SALES.length === 0) {
            this.logger.warn('Nenhum dado de venda encontrado em INITIAL_SALES')
            return [];
        }

        this.logger.log(`Calculando comissões para ${INITIAL_SALES.length} vendas`)
        const calculated = calculateComissionForSales(INITIAL_SALES);
        this.logger.log(`Comissão calculada para ${calculated.length} vendedores`)

        return calculated;
    }

    // Método para um possivel Feature Body Client Request
    async calculateCommission(sales: Sale[]): Promise<CommissionBySeller[]> {
        if (!sales || sales.length === 0) return [];

        const calculated = calculateComissionForSales(sales);

        return calculated;
  }
}
