// Configuração de juros para cálculos financeiros.
// Taxa de juros diária em formato decimal (2.5% = 0.025)
export interface InterestRateConfig {
    dailyRate: number;
}

// Multa de 2,5% ao dia.
export const INTEREST_RATE_CONFIG: InterestRateConfig = {
  dailyRate: 0.025,
};
