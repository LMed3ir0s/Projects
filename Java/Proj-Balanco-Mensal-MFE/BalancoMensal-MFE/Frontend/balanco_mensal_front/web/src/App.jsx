import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import Navbar from './components/Navbar/Navbar';
import Topbar from './components/Topbar/Topbar';
import AppRoutes from './routes/AppRoutes';

function App() {
  return (
    <BrowserRouter>
      <Topbar />
      <Navbar />
      <AppRoutes />
    </BrowserRouter>
  );
}

export default App;
