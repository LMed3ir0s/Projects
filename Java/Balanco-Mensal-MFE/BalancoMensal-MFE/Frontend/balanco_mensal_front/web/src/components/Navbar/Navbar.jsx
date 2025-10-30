import React from 'react';
import '../Navbar/Navbar.css';
import { Link } from 'react-router-dom';
import dashboardIcon from '../../assets/Dashboard.png';
import gastosIcon from '../../assets/Gastos.png';
import homeIcon from '../../assets/Home.png';



export default function Navbar() {
  return (
    <nav className="navbar"> {/* Navbar principal da aplicação */}
      <ul className="nav-list"> {/* Lista de navegação com os itens da navbar */}

        {/* Item 1 - link para a página Home */}
        <li className="nav-item">
          <Link to="/" className="nav-link">
            <img src={homeIcon} alt="Home" className="nav-icon" />
            Home
          </Link>
        </li>

        {/* Item 2 - link para a página de Gastos */}
        <li className="nav-item">
          <Link to="/gastos" className="nav-link">
            <img src={gastosIcon} alt="Gastos" className="nav-icon" />
            Gastos
          </Link>
        </li>

        {/* Item 3 - link para o Dashboard */}
        <li className="nav-item">
            <Link to="/dashboard" className="nav-link">
                <img src={dashboardIcon} alt="Dashboard" className="nav-icon" />
                Dashboard
            </Link>
        </li>
      </ul>
    </nav>
  );
}
