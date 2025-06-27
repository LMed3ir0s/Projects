package com.lucasmedeiros.balanco_financeiro.controller;

import com.lucasmedeiros.balanco_financeiro.model.Gasto;
import com.lucasmedeiros.balanco_financeiro.service.GastoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    // => Cria um Gasto
    @Operation(summary = "Cria um novo gasto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Gasto criado com sucesso!",
                    content = @Content(schema = @Schema(implementation = Gasto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Gasto> criaGasto(@RequestBody Gasto gasto) {
        Gasto gastoSalvo = gastoService.criaGasto(gasto);
        return ResponseEntity.status(201).body(gastoSalvo);
    }

    // => Lista todos os Gastos
    @Operation(summary = "Lista todos os gastos")
    @ApiResponse(responseCode = "200", description = "Lista de gastos",
            content = @Content(schema = @Schema(implementation = Gasto.class)))
    @GetMapping
    public List<Gasto> listaGastos() {
        return gastoService.listarGastos();
    }

    // => Atualiza um Gasto
    @Operation(summary = "Atualiza um gasto pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Gasto Atualizado",
                    content = @Content(schema = @Schema(implementation = Gasto.class))),
            @ApiResponse(responseCode = "404", description = "Gasto não encontrado")
    })
    @PutMapping("/{id}")
    public Gasto atualizaGasto(@PathVariable Long id, @RequestBody Gasto gasto) {
        return gastoService.atualizaGasto(id, gasto);
    }

    // => Deleta um Gasto
    @Operation(summary = "Deleta um gasto pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Gasto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Gasto não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaGasto(@PathVariable Long id) {
        gastoService.deletaGasto(id);
        return ResponseEntity.ok("Gasto deletado com sucesso!");
    }

    // => Filtro por Categoria
    @Operation(summary = "Busca gastos por categoria")
    @ApiResponse(responseCode = "200", description = "Lista de gastos filtrados por categoria",
            content = @Content(schema = @Schema(implementation = Gasto.class)))
    @GetMapping("/categoria/{categoria}")
    public List<Gasto> filtroCategoria(@PathVariable String categoria) {
        return gastoService.buscaCategoria(categoria);
    }

    // => Total Gastos
    @Operation(summary = "Calcula o total de todos os gastos")
    @ApiResponse(responseCode = "200", description = "Valor total dos gastos")
    @GetMapping("/total")
    public Double totalGastos() {
        return gastoService.calculaTotalGastos();
    }

    // => Filtro Intervalo de Datas
    @Operation(summary = "Filtra gastos por intervalo de datas")
    @ApiResponse(responseCode = "200", description = "Lista de gastos no intervalo de datas",
            content = @Content(schema = @Schema(implementation = Gasto.class))
    )
    @GetMapping("/intervalo-data")
    public List<Gasto> filtroDatas(
            @Parameter(description = "Data inicial no formato yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @Parameter(description = "Data inicial no formato yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return gastoService.filtroDatas(inicio, fim);
    }

    // => Gasto Total Periodo
    @Operation(summary = "Calcula o total de gastos em um período")
    @ApiResponse(responseCode = "200", description = "Valor total dos gastos no período")
    @GetMapping("/total-periodo")
    public Double gastoTotalPeriodo(
            @Parameter(description = "Data inicial no formato yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @Parameter(description = "Data inicial no formato yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return gastoService.gastoTotalPeriodo(inicio, fim);
    }

}
