//CUSTOM HOOK

import { useState } from 'react';
import { apiatualizarGasto } from '../../../api/gastos';

export default function useAtualizarGasto() {
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);
    const [sucesso, setSucesso] = useState (false);
    const atualizar = async (id, dadosAtualizados) => {
        setLoading(true);
        setErro(null);
        setSucesso(false);
    try {
        await apiatualizarGasto(id, dadosAtualizados);
        setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.mensagem || 'Erro ao atualizar gasto';
        setErro(mensagem);
    } finally {
        setLoading(false);
      }
    };
    return { atualizar, loading, erro, sucesso };
}