import React from 'react';
import './VoltarHome.css';
import VoltarIcon from '../../assets/Voltar.png';
import { Link } from 'react-router-dom';


export default function VoltarHome() {
    return(
        <div className="voltar-home-wrapper">
            <div className="voltar">
                <Link to="/">
                <img src={VoltarIcon} alt="Voltar" className="voltar-icon" />
                Voltar para Início
                </Link>
            </div>
        </div>
    );
}