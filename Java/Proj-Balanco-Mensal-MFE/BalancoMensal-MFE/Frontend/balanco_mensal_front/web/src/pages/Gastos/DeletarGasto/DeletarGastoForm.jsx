import React, { useState } from 'react';
import './DeletarGastoForm.css';

export default function DeletarGastoForm({ onSubmit, loading }){

    // => Estado controlado de cada campo
    const [formData, setFormData] = useState({
        id: '',
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
        <form className="form-deletar-gasto" onSubmit={handleSubmit}>
            <label>
                ID:
                <input
                    type="number"
                    name="id"
                    value={formData.id}
                    onChange={handleChange}
                    placeholder="Digite o ID do gasto"
                    required
                />
            </label>

            <button type="submit" disable={loading}>
                {loading ? 'Deletando...' : 'Deletar'}
            </button>
        </form>
        )
    }