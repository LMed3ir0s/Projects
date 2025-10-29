//CUSTOM HOOK

import { useState } from 'react';
import { apicriarGasto } from '../../../api/gastos';

export default function useCriarGasto() {
  const [loading, setLoading] = useState(false);
  const [erro, setErro] = useState(null);
  const [sucesso, setSucesso] = useState(false);
  const criarGasto = async (formDados) => {
    try {
      setLoading(true);
      setErro(null);
      setSucesso(false);
      await apicriarGasto(formDados);
      setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.message || err.message || 'Erro ao criar gasto'
        setErro(mensagem);
    } finally {
      setLoading(false);
    }
  };
  return { criarGasto, loading, erro, sucesso };
}