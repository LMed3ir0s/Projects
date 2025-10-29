import React from 'react';
import { Link } from 'react-router-dom';
import './TotalGastos.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useTotalGastos from './useTotalGastos';
import TotalGastosForm from './TotalGastosForm';

export default function TotalGastos() {
    console.log("Página - TotalGastos renderizado");

    const { totalGastos, erro, sucesso, loading } = useTotalGastos();

    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Total Gastos</h1>
                <p>
                    Obtenha o valor total de todos os gastos cadastrados.
                </p>
                <div className="total-gastos-form-wrapper">
                    <TotalGastosForm onSubmit={totalGastos} loading={loading} />
                    {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                    {sucesso && <p className="mensagem-sucesso">Lista de Gastos aplicada com sucesso!</p>}
                </div>
                <VoltarGastos />
            </div>
        </div>
    );
}