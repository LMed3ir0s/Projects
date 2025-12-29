import { Test, TestingModule } from '@nestjs/testing';
import { FinanceService } from '../../../modules/finance/services/finance.service';
import { CalculateOverduePaymentDto } from '../../../modules/finance/dtos/create-overdue-payment.dto';

describe('FinanceService', () => {
  let service: FinanceService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [FinanceService],
    }).compile();

    service = module.get<FinanceService>(FinanceService);
  });

  it('deve calcular zero de juros quando pago na data de vencimento', () => {
    // Arrange
    const dto: CalculateOverduePaymentDto = {
      amount: 100,
      overdueDate: '2025-12-10',
      paymentDate: '2025-12-10',
    };

    // Act
    const result = service.calculateOverduePayment(dto);

    // Assert
    expect(result.daysLate).toBe(0);
    expect(result.amount).toBe(100);
    expect(result.interest).toBe(0);
    expect(result.total).toBe(100);
  });

  it('deve calcular juros para pagamento em atraso', () => {
    // Arrange
    const dto: CalculateOverduePaymentDto = {
      amount: 100,                 // R$ 100,00
      overdueDate: '2025-12-10',
      paymentDate: '2025-12-14',   // 4 dias de atraso
    };

    // Act
    const result = service.calculateOverduePayment(dto);

    // Assert
    expect(result.daysLate).toBe(4);
    expect(result.amount).toBe(100);           // valor original
    expect(result.interest).toBeCloseTo(10, 2); // 100 * 0.025 * 4 = 10
    expect(result.total).toBeCloseTo(110, 2);   // 100 + 10
  });
});
