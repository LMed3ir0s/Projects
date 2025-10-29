//CUSTOM HOOK

import { useState } from 'react';
import { apitotalGastos } from '../../../api/gastos';

export default function useTotalGastos() {
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);
    const [sucesso, setSucesso] = useState (false);
    const totalGastos = async () => {
    try {
        setLoading(true);
        setErro(null);
        setSucesso(false);
        await apitotalGastos();
        setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.mensagem || 'Erro ao listar categoria';
        setErro(mensagem);
    } finally {
        setLoading(false);
      }
    };
    return { totalGastos, loading, erro, sucesso };
}