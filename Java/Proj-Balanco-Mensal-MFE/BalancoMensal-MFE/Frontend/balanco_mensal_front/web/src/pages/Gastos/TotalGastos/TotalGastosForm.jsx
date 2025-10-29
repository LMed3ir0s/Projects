import React, { useState } from 'react';
import './TotalGastosForm.css';

export default function TotalGastosForm({ onSubmit, loading }){

    // => Envia o formulário com os dados prontos
    const handleSubmit = (e) => {
        e.preventDefault(); // **Evita o reload da página\
        // Chama o método recebido via props
        onSubmit();
    };
    return(
        <form className="form-total_gastos" onSubmit={handleSubmit}>
            <button type="submit" disabled={loading}>
                {loading ? 'Calculando...' : 'Calcular'}
            </button>
        </form>
    )
}
