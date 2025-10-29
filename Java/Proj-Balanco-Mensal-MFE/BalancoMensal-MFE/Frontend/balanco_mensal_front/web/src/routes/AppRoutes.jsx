import React from 'react';
import {BrowserRouter, Routes, Route} from "react-router-dom";

import Home from "../pages/Home";
import Gastos from "../pages/Gastos/Gastos";
import Dashboard from "../pages/Dashboard/Dashboard"
import AtualizarGasto from "../pages/Gastos/AtualizarGasto";
import CriarGasto from "../pages/Gastos/CriarGasto";
import DeletarGasto from "../pages/Gastos/DeletarGasto";
import FiltroCategoria from "../pages/Gastos/FiltroCategoria";
import FiltroDatas from "../pages/Gastos/FiltroDatas";
import ListarGastos from "../pages/Gastos/ListarGastos";
import TotalGastos from "../pages/Gastos/TotalGastos";
import TotalPeriodo from "../pages/Gastos/TotalPeriodo";

export default function AppRoutes(){
    return (
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/gastos" element={<Gastos />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/gastos/atualizar" element={<AtualizarGasto />} />
                <Route path="/gastos/criar" element={<CriarGasto />} />
                <Route path="/gastos/deletar" element={<DeletarGasto />} />
                <Route path="/gastos/filtro-categoria" element={<FiltroCategoria />} />
                <Route path="/gastos/filtro-datas" element={<FiltroDatas />} />
                <Route path="/gastos/listar" element={<ListarGastos />} />
                <Route path="/gastos/total" element={<TotalGastos />} />
                <Route path="/gastos/total-periodo" element={<TotalPeriodo />} />
            </Routes>
            );
}