import React from 'react';
import { Link } from 'react-router-dom';
import './ListarGastos.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useListarGastos from './useListarGastos';
import ListarGastosForm from './ListarGastosForm';

export default function ListarGastos() {
    console.log("Página - ListarGastos renderizado");

    const { listarGastos, erro, sucesso, loading } = useListarGastos();

    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Listar Gastos</h1>
                <p>
                    Visualize todos os seus gastos registrados.
                </p>
                <div className="listar-gastos-form-wrapper">
                    <ListarGastosForm onSubmit={listarGastos} loading={loading} />
                    {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                    {sucesso && <p className="mensagem-sucesso">Lista de Gastos aplicada com sucesso!</p>}
                </div>
                <VoltarGastos />
            </div>
        </div>
    );
}