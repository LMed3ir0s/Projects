import { Test, TestingModule } from '@nestjs/testing';
import { FinanceController } from '../../../modules/finance/controllers/finance.controller';
import { FinanceService } from '../../../modules/finance/services/finance.service';
import { CalculateOverduePaymentDto } from '../../../modules/finance/dtos/create-overdue-payment.dto';
import { OverduePaymentResultDto } from 'src/modules/finance/dtos/overdue-payment-result.dto';

describe('FinanceController', () => {
  let controller: FinanceController;
  let service: FinanceService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [FinanceController],
      providers: [
        {
          provide: FinanceService,
          useValue: {
            calculateOverduePayment: jest.fn(),
          },
        },
      ],
    }).compile();

    controller = module.get<FinanceController>(FinanceController);
    service = module.get<FinanceService>(FinanceService);
  });

  it('deve estar definido', () => {
    // Arrange / Act / Assert
    expect(controller).toBeDefined();
  });

  it('deve delegar o cálculo para o FinanceService e retornar o resultado', () => {
    // Arrange
    const dto: CalculateOverduePaymentDto = {
      amount: 100,
      overdueDate: '2025-12-10',
      paymentDate: '2025-12-14',
    };

    const serviceResult: OverduePaymentResultDto = {
      daysLate: 4,
      amount: 100,
      interest: 10,
      total: 110,
    };

    jest
      .spyOn(service, 'calculateOverduePayment')
      .mockReturnValue(serviceResult);

    // Act
    const result = controller.calculateOverduePayment(dto);

    // Assert
    expect(service.calculateOverduePayment).toHaveBeenCalledTimes(1);
    expect(service.calculateOverduePayment).toHaveBeenCalledWith(dto);
    expect(result).toEqual(serviceResult);
  });
});
