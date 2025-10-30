//CUSTOM HOOK

import { useState } from 'react';
import { apifiltroDatas } from '../../../api/gastos';

export default function useFiltroDatas() {
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);
    const [sucesso, setSucesso] = useState (false);
    const filtroDatas = async (formDado) => {
    try {
        setLoading(true);
        setErro(null);
        setSucesso(false);
        await apifiltroDatas(formDado);
        setSucesso(true);
    } catch (err) {
        console.log(err.response.data);
        const mensagem = err.response?.data?.mensagem || 'Erro ao filtrar categoria';
        setErro(mensagem);
    } finally {
        setLoading(false);
      }
    };
    return { filtroDatas, loading, erro, sucesso };
}