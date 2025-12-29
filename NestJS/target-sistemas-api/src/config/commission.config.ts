import { toCents } from '../shared/utils/money-utils';

// Valor em centavos (R$1,00 = 100)
// Percentual em formato decimal (1% = 0.01
export interface CommissionRule {
    min: number,
    max?: number,
    rate: number 
}

// Vendas abaixo de R$100,00 não gera comissão
// Vendas abaixo de R$500,00 gera 1% de comissão
// A partir de R$500,00 gera 5% de comissão
export const COMISSION_RULES: CommissionRule[] = [
    // Sem comissão
    {
        min: 0,
        max: toCents(100),
        rate: 0
    },

    // 1% de comissão
    {
        min: toCents(100),
        max: toCents(500),
        rate: 0.01
    },

    // 5% de comissão
    {
        min: toCents(500),
        rate: 0.05
    }
]