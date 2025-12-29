import { Body, Controller, Get, Post } from "@nestjs/common";
import { SalesService } from "../services/sales.service";
import { CommissionResultDto } from "../dtos/commission-result.dto";
import { toReal, toCents } from "../../../shared/utils/money-utils";
import { CalculateCommissionRequestDto } from "../dtos/calculate-commission-request.dto";
import { ApiBody, ApiOkResponse, ApiOperation, ApiTags } from "@nestjs/swagger";

@ApiTags('sales')
@Controller('sales')
export class SalesController {
    constructor(private readonly salesService: SalesService) {}

    @Get('commissions')
    @ApiOperation({ summary: 'Listar comissões calculadas a partir do JSON (ETL simulado)'})
    @ApiOkResponse({ type: [CommissionResultDto]})
    async getCommissionsFromJson(): Promise<CommissionResultDto[]> {
        const results = await this.salesService.calculateCommissionFromJson();
        const convertResult = results.map((item) => ({
            sellerName: item.sellerName,
            totalSales: toReal(item.totalSalesInCents),
            totalCommission: toReal(item.totalCommissionInCents)
        }));

        return convertResult;
    }

    // Endpoint preparado para futura feature: calcular comissão a partir de vendas enviadas no body.
    @Post('commissions')
    @ApiOperation({ summary: 'Calcula comissões a partir do Body Request'})
    @ApiBody({type: CalculateCommissionRequestDto})
    @ApiOkResponse({ type: [CommissionResultDto]})
    async calculateCommissionsFromBody(@Body() request: CalculateCommissionRequestDto): Promise<CommissionResultDto[]> {
        // Adapta DTO de entrada (R$) para domínio (centavos)
        const sales = (request.sales ?? []).map((item) => ({
        sellerName: item.sellerName,
        valueInCents: toCents(item.value)
        }));

        const results = await this.salesService.calculateCommission(sales);
        const convertResult = results.map((item) => ({
            sellerName: item.sellerName,
            totalSales: toReal(item.totalSalesInCents),
            totalCommission: toReal(item.totalCommissionInCents)
        }));

        return convertResult;
    }
}