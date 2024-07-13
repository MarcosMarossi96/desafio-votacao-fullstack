import { useNavigate } from 'react-router-dom'
import styles from './styles.module.scss'

const Menu = () => {
    const navigate = useNavigate();

    return (
        <div id="nav-container">
            <nav className={styles.menu}>
                <ul>
                    <li onClick={() => navigate("/")}><a>Início</a></li>
                    <li onClick={() => navigate("/pauta")}><a>Pautas</a></li>
                    <li onClick={() => navigate("/associado")}><a>Associados</a></li>
                </ul>
            </nav>
        </div>
    )
}

export default Menu