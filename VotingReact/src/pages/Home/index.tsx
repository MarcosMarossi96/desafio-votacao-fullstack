import TeamWorkIcon from "../../assets/teamwork"
import Menu from "../../components/Menu"
import styles from "./home.module.scss"

/**
 * @description Home page to introduce cooperative theme.
 * @returns {JSX.Element}
 */
const Home = () => {
    return (
        <div>
            <Menu />

            <div className={styles.homeContainer}>
                <TeamWorkIcon height="240px" width="240px" />

                <h2>Seja bem-vindo ao sistema de voto cooperativo!</h2>

                <p>
                    É com imensa alegria que recebemos cada um de vocês neste espaço dedicado ao cooperativismo. Aqui, acreditamos na força do coletivo e na importância da participação de todos. No nosso modelo, cada associado tem um voto, e todas as decisões são tomadas em sessões de votação, de maneira democrática e transparente.

                    Estamos aqui para construir juntos um futuro mais justo e colaborativo, onde cada opinião conta e faz a diferença. Sua participação é essencial para o sucesso de nossa cooperativa, e queremos que você se sinta verdadeiramente parte dessa grande família.
                </p>
            </div>
        </div>
    )
}

export default Home