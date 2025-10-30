import React, { useState } from 'react';
import './FiltroCategoriaForm.css';

export default function FiltroCategoriaForm({ onSubmit, loading }){

    // => Estado controlado de cada campo
    const [formData, setFormData] = useState({
        categoria: '',
        })

    // => Atualiza o estado conforme o usuário digita
    const handleChange = (e) => {
        const { name, value } = e.target;

        setFormData((prevData) => ({...prevData,[name]: value}));
        };

    // => Envia o formulário com os dados prontos
    const handleSubmit = (e) => {
        e.preventDefault(); // **Evita o reload da página\
        // Chama o método recebido via props
        onSubmit(formData);
    };

    return(
        <form className="form-filtro_categoria-gasto" onSubmit={handleSubmit}>
            <label>
                Categoria:
                <input
                    type="text"
                    name="categoria"
                    value={formData.categoria}
                    onChange={handleChange}
                    placeholder="Digite a Categoria"
                    required
                />
            </label>

            <button type="submit" disable={loading}>
                {loading ? 'Filtrando...' : 'Filtrar'}
            </button>
        </form>
        )
    }