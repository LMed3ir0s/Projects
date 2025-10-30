//CUSTOM HOOK

import { useState } from 'react';
import { apilistarGastos } from '../../../api/gastos';

export default function useListarGastos() {
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);
    const [sucesso, setSucesso] = useState (false);
    const listarGastos = async () => {
    try {
        setLoading(true);
        setErro(null);
        setSucesso(false);
        await apilistarGastos();
        setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.mensagem || 'Erro ao listar categoria';
        setErro(mensagem);
    } finally {
        setLoading(false);
      }
    };
    return { listarGastos, loading, erro, sucesso };
}