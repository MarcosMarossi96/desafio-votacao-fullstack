import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './app.module.scss'
import Home from './pages/Home';
import Agenda from './pages/Agenda';
import Associate from './pages/Associate';
import NewAgenda from './pages/Agenda/New';
import NewAssociate from './pages/Associate/New';
import Session from './pages/Session';
import 'react-toastify/dist/ReactToastify.css';
import Results from './pages/Session/Results';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/pauta" element={<Agenda />} />
                <Route path="/nova-pauta" element={<NewAgenda />} />
                <Route path="/associado" element={<Associate />} />
                <Route path="/novo-associado" element={<NewAssociate />} />
                <Route path="/sessao/:id" element={<Session />} />
                <Route path="/resultado/:id" element={<Results />} />
            </Routes>
        </BrowserRouter>
    )
}

export default App
