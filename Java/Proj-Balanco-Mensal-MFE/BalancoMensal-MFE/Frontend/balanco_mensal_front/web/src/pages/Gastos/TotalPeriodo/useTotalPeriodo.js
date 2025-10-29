//CUSTOM HOOK

import { useState } from 'react';
import { apitotalPeriodo } from '../../../api/gastos';

export default function useTotalPeriodo() {
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);
    const [sucesso, setSucesso] = useState (false);
    const totalPeriodo = async (formDado) => {
    try {
        setLoading(true);
        setErro(null);
        setSucesso(false);
        await apitotalPeriodo(formDado);
        setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.mensagem || 'Erro ao calcular gastos';
        setErro(mensagem);
    } finally {
        setLoading(false);
      }
    };
    return { totalPeriodo, loading, erro, sucesso };
}

