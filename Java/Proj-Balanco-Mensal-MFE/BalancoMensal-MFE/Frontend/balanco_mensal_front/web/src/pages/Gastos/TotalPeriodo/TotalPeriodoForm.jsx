import React, { useState } from 'react';
import './TotalPeriodoForm.css';

export default function TotalPeriodoForm({ onSubmit, loading }){

    // => Estado controlado de cada campo
    const [formData, setFormData] = useState({
        data_inicio: '',
        data_fim: '',
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
        <form className="form-total-periodo-gasto" onSubmit={handleSubmit}>
            <label>
                Data inicio:
                <input
                    type="date"
                    name="data_inicio"
                    value={formData.data_inicio}
                    onChange={handleChange}
                    placeholder="Digite uma data"
                    required
                />
            </label>

            <label>
                Data fim:
                <input
                    type="date"
                    name="data_fim"
                    value={formData.data_fim}
                    onChange={handleChange}
                    placeholder="Digite uma data"
                    required
                />
            </label>

            <button type="submit" disable={loading}>
                {loading ? 'Calculando...' : 'Calcular'}
            </button>
        </form>
        )
    }