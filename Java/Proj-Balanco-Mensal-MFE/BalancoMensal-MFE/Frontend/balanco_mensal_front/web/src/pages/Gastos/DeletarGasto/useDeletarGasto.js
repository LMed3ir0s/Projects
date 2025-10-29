//CUSTOM HOOK

import { useState } from 'react';
import { apideletarGasto } from '../../../api/gastos';

export default function useDeletarGasto() {
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);
    const [sucesso, setSucesso] = useState (false);
    const deletarGasto = async (formDado) => {
    try {
        setLoading(true);
        setErro(null);
        setSucesso(false);
        await apideletarGasto(formDado);
        setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.mensagem || 'Erro ao deletar gasto';
        setErro(mensagem);
    } finally {
        setLoading(false);
      }
    };
    return { deletarGasto, loading, erro, sucesso };
}