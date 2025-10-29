import React from 'react';
import { Link } from 'react-router-dom';
import './FiltroDatas.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useFiltroDatas from './useFiltroDatas';
import FiltroDatasForm from './FiltroDatasForm';

export default function FiltroDatas() {
    console.log("Página - FiltroDatas renderizado");

    const { filtroDatas, erro, sucesso, loading } = useFiltroDatas();

    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Filtro Datas</h1>
                <p>
                    Selecione duas datas para filtrar.
                </p>
                <div className="filtro-datas-form-wrapper">
                    <FiltroDatasForm onSubmit={filtroDatas} loading={loading} />
                    {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                    {sucesso && <p className="mensagem-sucesso">Filtro aplicado com sucesso!</p>}
                </div >
                <VoltarGastos />
            </div>
        </div>
    );
}