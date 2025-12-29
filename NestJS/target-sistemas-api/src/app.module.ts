import { Module } from '@nestjs/common';
import { SalesModule } from './modules/sales/sales.module';
import { InventoryModule } from './modules/inventory/inventory.module';
import { FinanceModule } from './modules/finance/finance.module';

@Module({
  imports: [SalesModule, InventoryModule, FinanceModule]
})
export class AppModule {}
