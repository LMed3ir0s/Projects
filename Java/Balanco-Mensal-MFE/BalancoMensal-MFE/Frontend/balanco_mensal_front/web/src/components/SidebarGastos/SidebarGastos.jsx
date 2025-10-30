import { Link } from 'react-router-dom';
import './SidebarGastos.css';

export default function SidebarGastos() {
  return (
    <aside className="sidebar">
      <h2>Menu</h2>

      <ul className="side-list">

        <li className="side-item">
            <Link to="/gastos/atualizar" className="side-link">
            Atualizar
            </Link>
        </li>

        <li className="side-item">
            <Link to="/gastos/criar" className="side-link">
            Criar
            </Link>
        </li>

        <li className="side-item">
            <Link to="/gastos/deletar" className="side-link">
            Deletar
            </Link>
        </li>

        <li className="side-item">
            <Link to="/gastos/filtro-categoria" className="side-link">
            Filtro Categoria
            </Link>
        </li>

        <li className="side-item">
            <Link to="/gastos/filtro-datas" className="side-link">
            Filtro Datas
            </Link>
        </li>

        <li className="side-item">
            <Link to="/gastos/listar" className="side-link">
            Listar
            </Link>
        </li>

        <li className="side-item">
            <Link to="/gastos/total" className="side-link">
            Total
            </Link>
        </li>

        <li className="side-item">
            <Link to="/gastos/total-periodo" className="side-link">
            Total Período
            </Link>
        </li>
      </ul>
    </aside>
  );
}
