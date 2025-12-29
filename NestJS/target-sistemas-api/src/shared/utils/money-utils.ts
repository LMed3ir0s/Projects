// Converte reais para centavos (R$ 1,00 -> 100).
export const toCents = (valueInReais: number): number =>
    Math.round(valueInReais * 100);

// Converte centavos para reais (100 -> R$1.00).
export const toReal = (valueInCents: number): number =>
    valueInCents / 100;