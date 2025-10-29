import React from 'react';
import { Link } from 'react-router-dom';
import './FiltroCategoria.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useFiltroCategoria from './useFiltroCategoria';
import FiltroCategoriaForm from './FiltroCategoriaForm';

export default function FiltroCategoria() {
    console.log("Página - FiltroCategoria renderizado");

    const { filtroCategoria, erro, sucesso, loading } = useFiltroCategoria();

    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Filtro Categoria</h1>
                <p>
                    Escolha uma categoria cadastrada para aplicar o filtro."
                </p>
                <div className="filtro-categoria-form-wrapper">
                    <FiltroCategoriaForm onSubmit={filtroCategoria} loading={loading} />
                    {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                    {sucesso && <p className="mensagem-sucesso">Filtro aplicado com sucesso!</p>}
                </div>
                <VoltarGastos className="filtro-categoria-voltar-wrapper"/>
            </div>
        </div>
    );
}