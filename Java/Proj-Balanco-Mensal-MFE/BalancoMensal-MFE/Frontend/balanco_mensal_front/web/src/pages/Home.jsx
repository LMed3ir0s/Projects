import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css'


export default function Home() {
    console.log("Página - Home renderizado");

    return (
    <div className="home-container">
        <section class="project-intro">
          <h1>Bem-vindo ao Sistema de Balanço Mensal</h1>
          <p>Este é um projeto Fullstack desenvolvido com foco em controle financeiro.</p>
          <ul>
            <h2>Stack Utilizada:</h2>
            <li><strong>Banco de Dados:</strong> PostgreSQL</li>
            <li><strong>Backend:</strong> Java, Spring Boot</li>
            <li><strong>Testes:</strong> JUnit, Mockito, Spring Test</li>
            <li><strong>Frontend:</strong> React + Vite, JavaScript, CSS</li>
          </ul>
        </section>
        <Link to="https://www.linkedin.com/in/lucas-medeiros-ramos-1573741bb/"> Entre em contato </Link>
    </div>
  );
}
