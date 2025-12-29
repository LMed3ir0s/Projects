import { Test, TestingModule } from '@nestjs/testing';
import { SalesController } from '../../../modules/sales/controllers/sales.controller';
import { SalesService } from '../../../modules/sales/services/sales.service';
import { CommissionBySeller } from '../../../modules/sales/domain/enitities/commission-seller.entity';
import { toReal } from '../../../shared/utils/money-utils';

describe('SalesController', () => {
  let controller: SalesController;
  let service: SalesService;

  beforeEach(async () => {
    // Arrange: módulo de teste com controller + service mockado
    const module: TestingModule = await Test.createTestingModule({
      controllers: [SalesController],
      providers: [
        {
          provide: SalesService,
          useValue: {
            calculateCommissionFromJson: jest.fn(),
            calculateCommission: jest.fn(),
          },
        },
      ],
    }).compile();

    controller = module.get<SalesController>(SalesController);
    service = module.get<SalesService>(SalesService);
  });

  it('deve mapear o resultado de centavos para reais em getCommissions', async () => {
    // Arrange
    const domainResult: CommissionBySeller[] = [
      {
        sellerName: 'João',
        totalSalesInCents: 10000,
        totalCommissionInCents: 500,
      },
    ];
    (service.calculateCommissionFromJson as jest.Mock).mockResolvedValueOnce(
      domainResult,
    );

    // Act
    const result = await controller.getCommissionsFromJson();

    // Assert
    expect(service.calculateCommissionFromJson).toHaveBeenCalled();
    expect(result).toEqual([
      {
        sellerName: 'João',
        totalSales: toReal(10000),
        totalCommission: toReal(500),
      },
    ]);
  });
});
