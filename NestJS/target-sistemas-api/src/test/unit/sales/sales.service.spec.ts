// src/test/unit/sales/sales.service.spec.ts
import { SalesService } from '../../../modules/sales/services/sales.service';
import { CommissionBySeller } from '../../../modules/sales/domain/enitities/commission-seller.entity';
import { Sale } from '../../../modules/sales/domain/enitities/sale.entity';
import * as calculator from '../../../modules/sales/domain/helpers/commission-calculator';

// Mock da função de domínio para isolar o service
jest.mock('../../../modules/sales/domain/helpers/commission-calculator');

describe('SalesService', () => {
  let service: SalesService;

  beforeEach(() => {
    service = new SalesService();
  });

  it('deve retornar [] quando lista de vendas estiver vazia', async () => {
    // Arrange
    const sales: Sale[] = [];

    // Act
    const result = await service.calculateCommission(sales);

    // Assert
    expect(result).toEqual([]);
  });

  it('deve delegar para calculateComissionForSales com vendas informadas', async () => {
    // Arrange
    const sales: Sale[] = [
      { sellerName: 'João', valueInCents: 10000 },
      { sellerName: 'Maria', valueInCents: 20000 },
    ];
    const expected: CommissionBySeller[] = [
      {
        sellerName: 'João',
        totalSalesInCents: 10000,
        totalCommissionInCents: 0,
      },
    ];

    (calculator.calculateComissionForSales as jest.Mock).mockReturnValueOnce(
      expected,
    );

    const service = new SalesService();

    // Act
    const result = await service.calculateCommission(sales);

    // Assert
    expect(calculator.calculateComissionForSales).toHaveBeenCalledWith(sales);
    expect(result).toBe(expected);
  });
});
