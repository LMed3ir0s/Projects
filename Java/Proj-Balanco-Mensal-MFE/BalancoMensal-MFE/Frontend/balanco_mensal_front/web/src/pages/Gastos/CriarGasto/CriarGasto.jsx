import React from 'react';
import { Link } from 'react-router-dom';
import './CriarGasto.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useCriarGasto from './useCriarGasto';
import CriarGastoForm from './CriarGastoForm'

export default function CriarGastos() {
    console.log("Página - CriarGastos renderizado");

    const { criarGasto, loading, erro, sucesso } = useCriarGasto();

    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Criar Gasto</h1>
                <h2>Preencha os campos abaixo para registrar um novo gasto no sistema.</h2>
                <p>ID será gerado automaticamente.</p>
                <div className="criar-form-wrapper">
                <CriarGastoForm onSubmit={criarGasto} loading={loading} />
                {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                {sucesso && <p className="mensagem-sucesso">Gasto criado com sucesso!</p>}
                </div>
                <VoltarGastos className="criar-voltar-wrapper" />
            </div>
        </div>
    );
}