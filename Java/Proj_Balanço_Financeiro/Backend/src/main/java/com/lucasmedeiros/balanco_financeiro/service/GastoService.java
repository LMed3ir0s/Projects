package com.lucasmedeiros.balanco_financeiro.service;


import com.lucasmedeiros.balanco_financeiro.model.Gasto;
import com.lucasmedeiros.balanco_financeiro.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    // => Cria Gasto
    public Gasto criaGasto(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    // => Lista Gastos
    public List<Gasto> listarGastos() {
        return gastoRepository.findAll();
    }

    // => Busca Gasto por ID
    public Gasto buscarGastoPorId(Long id) {
        return gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto não encontrado com ID: " + id));
    }

    // => Atualiza um Gasto
    public Gasto atualizaGasto(Long id, Gasto gastoAtualizado) {
        Optional<Gasto> gastoExistente = gastoRepository.findById(id);

        if (gastoExistente.isEmpty()) {
            throw new RuntimeException("Gasto não encontrado com ID: " + id);
        }

        Gasto gasto = gastoExistente.get();
        gasto.setDescricao(gastoAtualizado.getDescricao());
        gasto.setValor(gastoAtualizado.getValor());
        gasto.setData(gastoAtualizado.getData());
        gasto.setCategoria(gastoAtualizado.getCategoria());
        return gastoRepository.save(gasto);
    }

    // => Deleta um Gasto
    public Gasto deletaGasto(Long id) {
        Optional<Gasto> gastoExistente = gastoRepository.findById(id);

        if (gastoExistente.isEmpty()) {
            throw new RuntimeException("Gasto não encontrado com ID: " + id);
        }
        Gasto gastoDeletado = gastoExistente.get();
        gastoRepository.deleteById(id);
        return gastoDeletado;
    }

    // => Busca Categoria
    public List<Gasto> buscaCategoria(String categoria) {
        List<Gasto> categoriaExistente = gastoRepository.findByCategoria(categoria);

        if (categoriaExistente.isEmpty()) {
            throw new RuntimeException("Categoria " + categoria + " não encontrada");
        }
        return categoriaExistente;
    }

    // => Total Gastos
    public Double calculaTotalGastos() {
        Double total = gastoRepository.calcularTotalGastos();
        if (total == null) {
            return 0.0;
        }
        return total;
    }

    // => Filtro Intervalo de Datas
    public List<Gasto> filtroDatas(LocalDate inicio, LocalDate fim) {
        List<Gasto> gastos = gastoRepository.findByDataBetween(inicio, fim);
        if (gastos.isEmpty()) {
            throw new RuntimeException("Nenhum gasto encontrado no intervalo de datas informado.");
        }
        return gastos;
    }

    // => Gasto Total Periodo
    public Double gastoTotalPeriodo(LocalDate inicio, LocalDate fim) {
        Double total = gastoRepository.gastoTotalPeriodo(inicio, fim);
        if (total == null) {
            return 0.0;
        }
        return total;
    }

}
