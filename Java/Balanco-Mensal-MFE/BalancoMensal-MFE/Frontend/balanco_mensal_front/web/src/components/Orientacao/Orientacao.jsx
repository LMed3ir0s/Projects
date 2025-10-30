import React from 'react';
import './Orientacao.css';
import LampadaIcon from '../../assets/Lampada.png';

export default function Orientacao({texto}) {
    return (
        <div className="orientacao">
            <img src={LampadaIcon} alt="Dica" className="orientacao-icon" />
            {texto}
        </div>
    );
}
