import React, { useState } from 'react';
import './ListarGastosForm.css';

export default function ListarGastosForm({ onSubmit, loading }){

    // => Envia o formulário com os dados prontos
    const handleSubmit = (e) => {
        e.preventDefault(); // **Evita o reload da página\
        // Chama o método recebido via props
        onSubmit();
    };
    return(
        <form className="form-listar_gastos" onSubmit={handleSubmit}>
            <button type="submit" disabled={loading}>
                {loading ? 'Listando...' : 'Listar'}
            </button>
        </form>
    )
}
