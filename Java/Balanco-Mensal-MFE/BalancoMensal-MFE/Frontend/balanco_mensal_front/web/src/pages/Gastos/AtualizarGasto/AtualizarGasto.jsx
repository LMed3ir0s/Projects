import React from 'react';
import { Link } from 'react-router-dom';
import './AtualizarGasto.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useAtualizarGasto from './useAtualizarGasto';
import AtualizarGastoForm from './AtualizarGastoForm';

export default function AtualizarGasto() {
    console.log("Página - AtualizarGastos renderizado");

    const { atualizar, erro, sucesso, loading } = useAtualizarGasto();

    const handleSubmit = (id, dadosAtualizados) => {
        console.log('ID:', id);
        console.log('Dados para atualizar:', dadosAtualizados);
        atualizar(id, dadosAtualizados);
        };
    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Atualizar Gasto</h1>
                <h2>
                    Para atualizar um gasto, entre com o ID e o(s) campo(s) que deseja atualizar:
                </h2>
                <p>É possível atualizar apenas um ou mais campos.</p>
                <div className="atualizar-form-wrapper">
                    <AtualizarGastoForm onSubmit={handleSubmit} loading={loading} />
                    {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                    {sucesso && <p className="mensagem-sucesso">Gasto atualizado com sucesso!</p>}
                </div>
                <VoltarGastos className="atualizar-voltar-wrapper" />
            </div>
        </div>
    );
}
