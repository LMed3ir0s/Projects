import { COMISSION_RULES } from '../../../../config/commission.config';
import { CommissionBySeller } from "../enitities/commission-seller.entity";
import { Sale } from "../enitities/sale.entity";

export const calculateComissionForSales = (sales: Sale[]): CommissionBySeller[] => {
    if (!sales || sales.length === 0) return [];

    const BySeller = new Map<string, {sales: number; commission: number}>();

    for (const sale of sales) {
        if (!sale || !sale.sellerName) continue;
        if (sale.valueInCents <= 0) continue;

        const rate = findCommissionRate(sale.valueInCents);
        const commissionValue = Math.round(sale.valueInCents * rate);
        const current = BySeller.get(sale.sellerName) ?? {sales: 0, commission: 0};

        // Accumulator
        current.sales += sale.valueInCents;
        current.commission += commissionValue
        BySeller.set(sale.sellerName, current);
    }

    const results: CommissionBySeller[] = Array.from(BySeller.entries()).map(
        ([sellerName, {sales, commission}]) => ({
            sellerName,
            totalSalesInCents: sales,
            totalCommissionInCents: commission,
        }) 
    )

    return results;
};

const findCommissionRate = (valueInCents: number): number => {
    const rule = COMISSION_RULES.find((r) => {
        const minOk = valueInCents >= r.min;
        const maxOk = r.max === undefined || valueInCents < r.max;

        return minOk && maxOk;
    });

    if (!rule) return 0;
    
    return rule.rate;
}