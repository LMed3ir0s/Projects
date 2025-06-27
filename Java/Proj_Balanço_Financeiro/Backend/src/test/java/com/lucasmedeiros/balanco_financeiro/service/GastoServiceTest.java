package com.lucasmedeiros.balanco_financeiro.service;

import com.lucasmedeiros.balanco_financeiro.model.Gasto;
import com.lucasmedeiros.balanco_financeiro.repository.GastoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GastoServiceTest {

    @Mock
    private GastoRepository gastoRepository;

    @InjectMocks
    private GastoService gastoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarGastoComSucesso() {
        // => Arrange
        Gasto gasto = new Gasto();
        gasto.setDescricao("Compra supermercado");
        gasto.setValor(BigDecimal.valueOf(150.0));

        when(gastoRepository.save(any(Gasto.class))).thenReturn(gasto);

        // => Act
        Gasto resultado = gastoService.criaGasto(gasto);

        // => Asserts
        assertNotNull(resultado);
        assertEquals("Compra supermercado", resultado.getDescricao());
        verify(gastoRepository, times(1)).save(any(Gasto.class));
    }

    @Test
    void deveListarTodosGastos() {
        // => Arrange
        Gasto gasto1 = new Gasto(
                "Luz",
                BigDecimal.valueOf(120.0),
                LocalDate.now(),
                "casa"
        );

        Gasto gasto2 = new Gasto(
                "Agua",
                BigDecimal.valueOf(60.0),
                LocalDate.now(),
                "Casa"
        );

        List<Gasto> listaDeGastos = Arrays.asList(gasto1, gasto2);

        when(gastoRepository.findAll()).thenReturn(listaDeGastos);

        // => Act
        List<Gasto> resultado = gastoService.listarGastos();

        // => Asserts
        assertThat(resultado).isNotEmpty();
        assertThat(resultado.size()).isEqualTo(2);
        assertThat(resultado.get(0).getDescricao()).isEqualTo("Luz");
        assertThat(resultado.get(1).getDescricao()).isEqualTo("Agua");
    }

    @Test
    void deveBuscarGastoPorId() {
        // => Arrange
        Long id = 1L;
        Gasto gasto = new Gasto(
                "Internet",
                BigDecimal.valueOf(130.0),
                LocalDate.now(),
                "Casa"
        );
        ReflectionTestUtils.setField(gasto, "id", id);

        Mockito.when(gastoRepository.findById(id)).thenReturn(Optional.of(gasto));

        // => Act
        Gasto resultado = gastoService.buscarGastoPorId(id);

        // => Asserts
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(id);
        assertThat(resultado.getDescricao()).isEqualTo("Internet");
        assertThat(resultado.getValor()).isEqualByComparingTo(BigDecimal.valueOf(130.0));
        assertThat(resultado.getCategoria()).isEqualTo("Casa");
    }

    @Test
    void deveAtualizarGasto() {
        // => Arrange
        Long id = 1L;
        Gasto gastoExistente = new Gasto(
                "PC",
                BigDecimal.valueOf(100.0),
                LocalDate.now(),
                "Casa"
        );
        ReflectionTestUtils.setField(gastoExistente, "id", id);

        Gasto gastoAtualizado = new Gasto(
                "Computador",
                BigDecimal.valueOf(90.0),
                LocalDate.now(),
                "Lazer"
        );

        Mockito.when(gastoRepository.findById(id)).thenReturn(Optional.of(gastoExistente));
        Mockito.when(gastoRepository.save(Mockito.any(Gasto.class))).thenAnswer(invocaton -> invocaton.getArgument(0));

        // => Act
        Gasto resultado = gastoService.atualizaGasto(id, gastoAtualizado);

        // => Asserts
        assertThat(resultado.getDescricao()).isEqualTo("Computador");
        assertThat(resultado.getValor()).isEqualByComparingTo(BigDecimal.valueOf(90.0));
        assertThat(resultado.getCategoria()).isEqualTo("Lazer");
    }

    @Test
    void deveDeletarGasto() {
        // => Arrange
        Long id = 1L;
        Gasto gastoExistente = new Gasto(
                "PC",
                BigDecimal.valueOf(100.0),
                LocalDate.now(),
                "Casa"
        );
        ReflectionTestUtils.setField(gastoExistente, "id", id);

        Mockito.when(gastoRepository.findById(id)).thenReturn(Optional.of(gastoExistente));

        // => Act
        Gasto resultado = gastoService.deletaGasto(id);

        // => Asserts
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(id);
        assertThat(resultado.getDescricao()).isEqualTo("PC");

        Mockito.verify(gastoRepository).deleteById(id);
    }

    @Test
    void deveCalcularTotalDeGastos() {
        // => Arrange
        List<Gasto> gastos = List.of(
                new Gasto("Mouse", BigDecimal.valueOf(50), LocalDate.now(), "Escritorio"),
                new Gasto("Teclado", BigDecimal.valueOf(150), LocalDate.now(), "Escritorio")
        );

        Mockito.when(gastoRepository.findAll()).thenReturn(gastos);

        // => Act
        Double total = gastoService.calculaTotalGastos();

        // => Assert
        assertEquals(200.0, total);
    }

    @Test
    void deveBuscarGastosPorCategoria() {
        // => Arrange
        String categoria = "Escritorio";
        List<Gasto> gastosCategoria = List.of(
                new Gasto("Mouse", BigDecimal.valueOf(150.0), LocalDate.now(), categoria),
                new Gasto("Teclado", BigDecimal.valueOf(250.0), LocalDate.now(), categoria)
        );

        Mockito.when(gastoRepository.findByCategoria(categoria)).thenReturn(gastosCategoria);

        // => Act
        List<Gasto> resultado = gastoService.buscaCategoria(categoria);

        // => Assert
        assertThat(resultado)
                .isNotNull()
                .hasSize(2)
                .extracting(Gasto::getDescricao)
                .containsExactly("Mouse", "Teclado");

        assertThat(resultado)
                .extracting(Gasto::getCategoria)
                .containsOnly("Escritorio");

        assertThat(resultado)
                .extracting(Gasto::getValor)
                .containsExactly(BigDecimal.valueOf(150.0), BigDecimal.valueOf(250.0));
    }


    @Test
    void deveFiltrarGastosPorIntervaloDeDatas() {
        // => Arrange
        LocalDate inicio = LocalDate.of(2025, 6, 1);
        LocalDate fim = LocalDate.of(2025, 6, 30);

        List<Gasto> gastosNoPeriodo = List.of(
                new Gasto("Conta Luz", BigDecimal.valueOf(120.0), LocalDate.of(2025, 6, 10), "Casa"),
                new Gasto("Conta Agua", BigDecimal.valueOf(80.0), LocalDate.of(2025, 6, 20), "Casa")
        );

        Mockito.when(gastoRepository.findByDataBetween(inicio, fim)).thenReturn(gastosNoPeriodo);

        // => Act
        List<Gasto> resultado = gastoService.filtroDatas(inicio, fim);

        // => Assert
        assertEquals(2, resultado.size());
        assertEquals("Conta Luz", resultado.get(0).getDescricao());
    }

    @Test
    void deveCalcularGastoTotalNoPeriodo() {
        // => Arrange
        LocalDate inicio = LocalDate.of(2025, 6, 1);
        LocalDate fim = LocalDate.of(2025, 6, 30);

        List<Gasto> gastos = List.of(
                new Gasto("Conta Luz", BigDecimal.valueOf(120.0), LocalDate.of(2025, 6, 10), "Casa"),
                new Gasto("Conta Agua", BigDecimal.valueOf(80.0), LocalDate.of(2025, 6, 20), "Casa")
        );

        Mockito.when(gastoRepository.findByDataBetween(inicio, fim)).thenReturn(gastos);

        // => Act
        Double resultado = gastoService.gastoTotalPeriodo(inicio, fim);

        // => Assert
        assertEquals(200.0, resultado);
    }

}
