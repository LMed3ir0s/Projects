import React from 'react';
import { Link } from 'react-router-dom';
import './TotalPeriodo.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useTotalPeriodo from './useTotalPeriodo';
import TotalPeriodoForm from './TotalPeriodoForm';

export default function TotaPeriodo() {
    console.log("Página - TotaPeriodo renderizado");

    const { totalPeriodo, erro, sucesso, loading } = useTotalPeriodo();

    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Total Periodo</h1>
                <p>
                    Selecione duas datas para obter o total de gastos no intervalo. Ex: 2025/07/01 à 2025/07/31
                </p>
                 <div className="total-periodo-form-wrapper">
                    <TotalPeriodoForm onSubmit={totalPeriodo} loading={loading} />
                    {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                    {sucesso && <p className="mensagem-sucesso">Total periodo calculado com sucesso!</p>}
                </div >
                <VoltarGastos />
            </div>
        </div>
    );
}