import React from 'react';
import { Link } from 'react-router-dom';
import './Gastos.css'
import SidebarGastos from '../../components/SidebarGastos/SidebarGastos';
import Orientacao from '../../components/Orientacao/Orientacao';
import VoltarHome from '../../components/Voltar/VoltarHome';

export default function Gastos() {
    console.log("Página - Gastos renderizado");
    return (
        <div className="gastos-layout">
            <SidebarGastos />
            <div className="gastos-container">
                <h1>Página de Gastos</h1>
                <p>
                    Controle total dos seus gastos mensais: crie, edite, exclua e filtre suas despesas de forma prática.
                </p>
                <div className="gastos-components">
                <Orientacao texto="Navegue pelas funcionalidades usando o menu lateral à esquerda" />
                <VoltarHome />
                </div>
            </div>
        </div>

    );
}
