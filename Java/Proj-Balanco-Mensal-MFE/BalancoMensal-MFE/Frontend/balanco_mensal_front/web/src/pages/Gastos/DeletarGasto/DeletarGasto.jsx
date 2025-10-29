import React from 'react';
import { Link } from 'react-router-dom';
import './DeletarGasto.css'
import SidebarGastos from '../../../components/SidebarGastos/SidebarGastos';
import VoltarGastos from '../../../components/Voltar/VoltarGastos';

import { useEffect } from 'react';
import useDeletarGasto from './useDeletarGasto';
import DeletarGastoForm from './DeletarGastoForm';

export default function DeletarGastos() {
    console.log("Página - DeletarGasto renderizado");

    const { deletarGasto, erro, sucesso, loading } = useDeletarGasto();

    return(
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Deletar Gasto</h1>
                <p>
                    Informe o ID do gasto que deseja excluir.
                </p>
                <div className="deletar-form-wrapper">
                    <DeletarGastoForm onSubmit={deletarGasto} loading={loading} />
                    {erro && <p className="mensagem-erro">Erro: {erro}</p>}
                    {sucesso && <p className="mensagem-sucesso">Gasto deletado com sucesso!</p>}
                </div>
                <VoltarGastos className="deletar-voltar-wrapper"/>
            </div>
        </div>
    );
}