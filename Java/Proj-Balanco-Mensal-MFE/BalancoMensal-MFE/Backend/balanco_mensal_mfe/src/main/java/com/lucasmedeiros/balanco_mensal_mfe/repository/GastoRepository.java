package com.lucasmedeiros.balanco_mensal_mfe.repository;

import com.lucasmedeiros.balanco_mensal_mfe.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    List<Gasto> findByCategoria(String categoria);

    @Query("SELECT SUM(g.valor) FROM Gasto g")
    Double calcularTotalGastos();

    @Query("SELECT g FROM Gasto g WHERE g.data BETWEEN :inicio AND :fim")
    List<Gasto> findByDataBetween(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.data BETWEEN :inicio AND :fim")
    Double gastoTotalPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}
