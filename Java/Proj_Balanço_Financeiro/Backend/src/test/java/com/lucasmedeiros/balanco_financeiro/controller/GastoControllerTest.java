package com.lucasmedeiros.balanco_financeiro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmedeiros.balanco_financeiro.model.Gasto;
import com.lucasmedeiros.balanco_financeiro.service.GastoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GastoController.class)
public class GastoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GastoService gastoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarGasto() throws Exception {
        // => Arrange
        Long id = 2L;
        Gasto novoGasto = new Gasto(
                "Webcam",
                BigDecimal.valueOf(90.0),
                LocalDate.now(),
                "Escritorio"
        );
        ReflectionTestUtils.setField(novoGasto, "id", id);

        Mockito.when(gastoService.criaGasto(Mockito.any(Gasto.class))).thenReturn(novoGasto);

        // => Act
        ResultActions resultado = mockMvc.perform(post("/gastos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoGasto)));

        // => Asserts
        resultado.andExpect(status().isCreated())
                .andExpect(jsonPath("$.descricao").value("Webcam"))
                .andExpect(jsonPath("$.valor").value(90.0))
                .andExpect(jsonPath("$.categoria").value("Escritorio"));
    }

    @Test
    void deveListarGastos() throws Exception {
        // => Arrange
        Long id1 = 1L;
        Gasto gasto1 = new Gasto(
                "Webcam",
                BigDecimal.valueOf(90.0),
                LocalDate.now(),
                "Escritorio"
        );
        ReflectionTestUtils.setField(gasto1, "id", id1);

        Long id2 = 2L;
        Gasto gasto2 = new Gasto(
                "Monitor",
                BigDecimal.valueOf(500.0),
                LocalDate.now(),
                "Escritorio"
        );
        ReflectionTestUtils.setField(gasto2, "id", id2);

        List<Gasto> listaDeGastos = Arrays.asList(gasto1, gasto2);

        Mockito.when(gastoService.listarGastos()).thenReturn(listaDeGastos);

        // => Act
        ResultActions resultado = mockMvc.perform(get("/gastos")
                .contentType(MediaType.APPLICATION_JSON));

        // => Asserts
        resultado.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(listaDeGastos.size()))
                .andExpect(jsonPath("$[0].descricao").value("Webcam"))
                .andExpect(jsonPath("$[1].categoria").value("Escritorio"));
    }

    @Test
    void deveAtualizarGasto() throws Exception {
        // => Arrange
        Long id = 1L;
        Gasto gastoAtualizado = new Gasto(
                "Monitor",
                BigDecimal.valueOf(500.0),
                LocalDate.now(),
                "Escritorio"
        );
        ReflectionTestUtils.setField(gastoAtualizado, "id", id);

        Mockito.when(gastoService.atualizaGasto(Mockito.eq(id), Mockito.any(Gasto.class))).thenReturn(gastoAtualizado);

        // => Act
        ResultActions resultado = mockMvc.perform(put("/gastos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gastoAtualizado))
        );

        // => Asserts
        resultado.andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("Monitor"))
                .andExpect(jsonPath("$.valor").value(500.0));
    }

    @Test
    void deveDeletarGasto() throws Exception {
        // => Arrange
        Long id = 1L;
        Gasto gastoExistente = new Gasto(
                "PC",
                BigDecimal.valueOf(100.0),
                LocalDate.now(),
                "Casa"
        );
        ReflectionTestUtils.setField(gastoExistente, "id", id);

        Mockito.when(gastoService.deletaGasto(Mockito.eq(id))).thenReturn(gastoExistente);

        // => Act
        ResultActions resultado = mockMvc.perform(delete("/gastos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        // => Asserts
        resultado.andExpect(status().isOk())
                .andExpect(content().string("Gasto deletado com sucesso!"));

        Mockito.verify(gastoService).deletaGasto(id);
    }

    @Test
    void deveFiltrarGastosPorCategoria() throws Exception {
        // => Arrange
        String categoria = "Escritorio";
        List<Gasto> gastos = List.of(
                new Gasto("Webcam", BigDecimal.valueOf(90.0), LocalDate.now(), categoria),
                new Gasto("Monitor", BigDecimal.valueOf(500.0), LocalDate.now(), categoria)
        );

        Mockito.when(gastoService.buscaCategoria(categoria)).thenReturn(gastos);

        // => Act
        ResultActions resultado = mockMvc.perform(get("/gastos/categoria/{categoria}", categoria)
                .contentType(MediaType.APPLICATION_JSON));

        // => Assert
        resultado.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].descricao").value("Webcam"))
                .andExpect(jsonPath("$[1].descricao").value("Monitor"));
    }

    @Test
    void deveFiltrarGastosPorIntervaloDeDatas() throws Exception {
        // => Arrange
        LocalDate inicio = LocalDate.of(2025, 6, 1);
        LocalDate fim = LocalDate.of(2025, 6, 30);

        List<Gasto> gastos = List.of(
                new Gasto("Mouse", BigDecimal.valueOf(50.0), LocalDate.of(2025, 6, 10), "Escritorio"),
                new Gasto("Teclado", BigDecimal.valueOf(150.0), LocalDate.of(2025, 6, 15), "Escritorio")
        );

        Mockito.when(gastoService.filtroDatas(inicio, fim)).thenReturn(gastos);

        // => Act
        ResultActions resultado = mockMvc.perform(get("/gastos/intervalo-data")
                .param("inicio", inicio.toString())
                .param("fim", fim.toString())
                .contentType(MediaType.APPLICATION_JSON));

        // => Assert
        resultado.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].descricao").value("Mouse"))
                .andExpect(jsonPath("$[1].descricao").value("Teclado"));
    }

    @Test
    void deveCalcularTotalGastos() throws Exception {
        // => Arrange
        Double totalGastos = 240.0;
        Mockito.when(gastoService.calculaTotalGastos()).thenReturn(totalGastos);

        // => Act
        ResultActions resultado = mockMvc.perform(get("/gastos/total")
                .contentType(MediaType.APPLICATION_JSON));

        // => Assert
        resultado.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(totalGastos));
    }

    @Test
    void deveCalcularTotalGastosPorPeriodo() throws Exception {
        // => Arrange
        LocalDate inicio = LocalDate.of(2025, 6, 1);
        LocalDate fim = LocalDate.of(2025, 6, 30);
        Double totalPeriodo = 200.0;

        Mockito.when(gastoService.gastoTotalPeriodo(inicio, fim)).thenReturn(totalPeriodo);

        // => Act
        ResultActions resultado = mockMvc.perform(get("/gastos/total-periodo")
                .param("inicio", inicio.toString())
                .param("fim", fim.toString())
                .contentType(MediaType.APPLICATION_JSON));

        // => Assert
        resultado.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(totalPeriodo));
    }

}
