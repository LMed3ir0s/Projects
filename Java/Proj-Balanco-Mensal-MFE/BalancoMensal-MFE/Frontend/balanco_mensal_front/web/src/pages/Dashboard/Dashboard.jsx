import React from 'react';
import { Link } from 'react-router-dom';
import './Dashboard.css'
import VoltarHome from '../../components/Voltar/VoltarHome';


export default function Dashboard(){
    console.log("Página - Dashboard renderizado");

    return(
        <div className="dashboard-container">
            <section class="dashboard-intro">
                <h1>Dashboard</h1>
                <p>Visualize gráficos com o resumo dos seus gastos.</p>
                <VoltarHome />
            </section>
        </div>
        );
    }