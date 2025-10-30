import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8081', // ajuste se estiver usando outra porta ou domínio
  headers: {'Content-Type': 'application/json',},
});

export default api;
