//CUSTOM HOOK

import { useState } from 'react';
import { apifiltroCategoria } from '../../../api/gastos';

export default function useFiltroCategoria() {
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);
    const [sucesso, setSucesso] = useState (false);
    const filtroCategoria = async (formDado) => {
    try {
        setLoading(true);
        setErro(null);
        setSucesso(false);
        await apifiltroCategoria(formDado);
        setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.mensagem || 'Erro ao filtrar categoria';
        setErro(mensagem);
    } finally {
        setLoading(false);
      }
    };
    return { filtroCategoria, loading, erro, sucesso };
}