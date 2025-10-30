import React from 'react';
import './VoltarGastos.css';
import VoltarIcon from '../../assets/Voltar.png';
import { Link } from 'react-router-dom';


export default function VoltarGastos() {
    return(
        <div className="voltar-gastos-wrapper">
            <div className="voltar">
                <Link to="/gastos">
                <img src={VoltarIcon} alt="Voltar" className="voltar-icon" />
                Voltar para Início
                </Link>
            </div>
        </div>
    );
}