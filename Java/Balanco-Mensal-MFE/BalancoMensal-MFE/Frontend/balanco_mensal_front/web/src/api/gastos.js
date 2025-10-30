import api from './axios';

export const apiatualizarGasto = (id, dadosAtualizados) =>
    api.put(`/gastos/${id}`, dadosAtualizados);

export const apicriarGasto = (dadosGasto) =>
    api.post(`/gastos`, dadosGasto)

export const apideletarGasto = (idGasto) =>
    api.delete(`/gastos`, idGasto)

export const apifiltroCategoria = (categoria) =>
    api.get(`/categoria/${categoria}`)

export const apifiltroDatas  = (data_inicio, data_fim) =>
    api.get(`/intervalo-data`,{
    params:{ inicio: data_inicio,fim: data_fim }
    })

export const apilistarGastos  = () =>
    api.get(`/gastos`)

export const apitotalGastos  = () =>
    api.get(`/total`)

export const apitotalPeriodo  = (data_inicio, data_fim) =>
    api.get(`/total-periodo`)
