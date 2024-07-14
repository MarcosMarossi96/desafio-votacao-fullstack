import { useLayoutEffect, useState } from "react"
import Body from "../../../components/Body"
import Votes from "../../../components/Votes"
import api from "../../../services"
import { useLocation } from "react-router-dom"
import { ApiErrorMessage } from "../../../TS/error-interfaces"
import { AxiosError } from "axios"
import { VoteDTO } from "../../../TS/vote-interfaces"
import NoData from "../../../components/NoData"
import { toast, ToastContainer } from "react-toastify"
import styles from "./results.module.scss"
import PartyIcon from "../../../assets/party-icon"

/**
 * @description Voting results by session identifier screen.
 * @returns {JSX.Element}
 */
const Results = () => {
    const location = useLocation();
    const [votes, setVotes] = useState<VoteDTO>()

    useLayoutEffect(() => {
        /**
         * Get and parse URL params, for example: '/resultado/1'.
         */
        const routeParams = location.pathname.split("/")
        const sessionId = routeParams[2]

        /**
         * Search the voting results by session.
         */
        api.get<VoteDTO>("/vote/result/" + sessionId)
            .then(res => {
                const sessionDTO = res.data;
                setVotes(sessionDTO)
            })
            .catch((error: AxiosError<ApiErrorMessage>) => {
                const message = error?.response?.data.message || "Ocorreu um erro no processamento!"
                toast(message, {
                    type: "error",
                })
            })
    }, [])

    return (
        <div>
            <ToastContainer />

            <Body>
                <div>

                    {votes ?
                        <>
                            <div className={styles.resultsContainer}>
                                <PartyIcon height="128px" width="128px" />

                                <h3>Obrigado por participar da votação!</h3>

                                <p>Confira os resultados abaixo:</p>

                                <Votes yesVote={votes.yesVote} noVote={votes.noVote} />
                            </div>
                        </>
                        :
                        <>
                            <NoData message="Nenhum resultado de uma sessão foi encontrada. Contate um administrador!" />
                        </>
                    }
                </div>
            </Body>
        </div>
    )
}

export default Results