import { useNavigate } from 'react-router-dom'
import styles from './styles.module.scss'

/**
 * @description Displays a navigation menu with links to different routes.
 * @returns {JSX.Element} Returns a React component representing a navigation menu.
 */
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