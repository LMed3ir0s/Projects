import React, { useState } from 'react';
import './CriarGastoForm.css';

export default function CriarGastoForm ({ onSubmit, loading }) {

    // => Estado controlado de cada campo
    const [formData, setFormData] = useState ({
        descricao: '',
        valor: '',
        data: '',
        categoria: '',
        });

    // => Atualiza o estado conforme o usuário digita
    const handleChange = (e) => {
        const { name, value } = e.target;

        setFormData((prevData) => ({...prevData,[name]: value}));
        };

    // => Envia o formulário com os dados prontos
    const handleSubmit = (e) => {
        e.preventDefault(); // **Evita o reload da página
        // Chama o método recebido via props
        onSubmit(formData);
    };

    return (
        <form className="form-criar-gasto" onSubmit={handleSubmit}>
            <label>
                Descrição:
                <input
                    type="text"
                    name="descricao"
                    value={formData.descricao}
                    onChange={handleChange}
                    placeholder="Digite a descrição"
                    required
                />
            </label>

            <label>
                Valor:
                <input
                    type="number"
                    name="valor"
                    value={formData.valor}
                    onChange={handleChange}
                    placeholder="Digite o valor R$ (apenas numero)"
                    required
                    />
            </label>

            <label>
                Data:
                <input
                    type="date"
                    name="data"
                    value={formData.data}
                    onChange={handleChange}
                    placeholder="Digite a data"
                    required
                />
            </label>

            <label>
                Categoria:
                <input
                    type="text"
                    name="categoria"
                    value={formData.categoria}
                    onChange={handleChange}
                    placeholder="Digite a categoria"
                    required
                />
            </label>

            <button type="submit" disabled={loading}>
                {loading ? 'Criando...' : 'Criar'}
            </button>
        </form>
        )
    }