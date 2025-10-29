import React, { useState } from 'react';
import './AtualizarGastoForm.css';

export default function AtualizarGastoForm({ onSubmit, loading }) {

    // => Estado controlado de cada campo
    const [formData, setFormData] = useState({
        id: '',
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

    const { id, ...resto } = formData;

    if (!id) {
          alert("Por favor, informe um ID válido");
          return;
        }

    //=> Cria um objeto apenas com os campos preenchidos (além do ID)
    const dadosParaEnviar = Object.entries(formData).reduce((acc, [key, value]) => {
      if (value !== '') acc[key] = value;
      return acc;
    }, {});

    console.log("ID:", id);
    console.log("Dados enviados:", dadosParaEnviar);

    // Chama o método recebido via props
    onSubmit(Number(id), dadosParaEnviar);
    };

    return (
        <form className="form-atualizar-gasto" onSubmit={handleSubmit}>
            <label>
                ID do Gasto:
                <input
                 type="number"
                 name="id"
                 value={formData.id}
                 onChange={handleChange}
                 placeholder="Digite ID existente"
                 required
                 />
            </label>
            <label>
                Descrição:
                <input
                     type="text"
                     name="descricao"
                     value={formData.descricao}
                     onChange={handleChange}
                     placeholder="Digite nova descrição"
                 />
            </label>
            <label>
                Valor:
                <input
                    type="number"
                    name="valor"
                    value={formData.valor}
                    onChange={handleChange}
                    step="0.01"
                    placeholder="Digite novo valor"
                />
            </label>
            <label>
                Data:
                <input
                     type="date"
                     name="data"
                     value={formData.data}
                     onChange={handleChange}
                     placeholder="Digite nova data" />
            </label>
            <label>
                Categoria:
                <input
                    type="text"
                    name="categoria"
                    value={formData.categoria}
                    onChange={handleChange}
                    placeholder="Digite nova categoria"
                />
            </label>
            <button type="submit" disabled={loading}>
                {loading ? 'Atualizando...' : 'Atualizar'}
            </button>
        </form>
        );
    }