import { INTEREST_RATE_CONFIG } from "../../config/interest-rate.config"

export const calculateInterest = (mainValue: number, daysLate: number): number => {
    const interestRate = INTEREST_RATE_CONFIG.dailyRate;

    if (daysLate <= 0) return 0;

    if(interestRate <= 0) return 0;

    const rate = mainValue * interestRate * daysLate;

    return rate;
}