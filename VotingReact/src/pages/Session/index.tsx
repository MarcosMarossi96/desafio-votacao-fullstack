import { FormEvent, useLayoutEffect, useState } from "react";
import Body from "../../components/Body";
import Input from "../../components/Input";
import styles from "./session.module.scss";
import Button from "../../components/Button";
import { MdThumbUp, MdThumbDown } from "react-icons/md";
import api from "../../services";
import { useLocation, useNavigate } from "react-router-dom";
import { SessionDTO } from "../../TS/session-interfaces";
import moment from "moment";
import NoData from "../../components/NoData";
import { AxiosError } from "axios";
import { ApiErrorMessage } from "../../TS/error-interfaces";
import { ToastContainer, toast } from "react-toastify";

/**
 * @description Voting session page.
 * @returns {JSX.Element}
 */
const Session = () => {
    const [sessionDTO, setSessionDTO] = useState<SessionDTO>()
    const [selectedIcon, setSelectedIcon] = useState<string | null>(null);
    const [disable, setDisable] = useState<boolean>(false);

    const navigate = useNavigate();

    /**
    * Get and parse URL params, for example: '/sessao/1'.
    */
    const location = useLocation();
    const routeParams = location.pathname.split("/")
    const sessionId = routeParams[2]

    const handleIconClick = (icon: string) => {
        setSelectedIcon(icon);
    };

    useLayoutEffect(() => {
        /**
         * Find session by Id and verify if session is closed.
         */
        api.get<SessionDTO>("/session/" + sessionId)
            .then(res => {
                const sessionDTO = res.data;
                setSessionDTO(sessionDTO)

                const endDate = moment(sessionDTO.end, "DD/MM/YYYY HH:mm:ss");
                const currentDate = moment();

                if (currentDate.isAfter(endDate)) {
                    setDisable(true)
                }
            })
            .catch((error: AxiosError<ApiErrorMessage>) => {
                const message = error?.response?.data.message || "Ocorreu um erro no processamento!"
                toast(message, {
                    type: "error",
                })
            })
    }, [])

    /**
     * @description Send the voting form data to the API.
     * @param event Form event.
     */
    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        const currentDate = moment().format("YYYY-MM-DD HH:mm:ss");
        const vote = selectedIcon === "positive";

        const formData = new FormData(event.currentTarget);
        const requestObject: Record<string, string> = {};

        formData.forEach((value, key) => {
            requestObject[key] = value.toString();
        });

        const requestBody = {
            vote,
            sessionId: sessionDTO!.id,
            cpf: requestObject["cpf"],
            currentDate,
        }

        /**
         * Register the associate's vote in the API.
         */
        api.post("/vote", requestBody)
            .then(() => {
                toast("Voto realizado com sucesso!", {
                    type: "success",
                })

                navigate("/resultado/" + sessionId)
            })
            .catch((error: AxiosError<ApiErrorMessage>) => {
                const message = error?.response?.data.message || "Ocorreu um erro no processamento!"
                toast(message, {
                    type: "error",
                })
            })
    }

    /**
     * @description Change expire session label
     * @returns {string} Expire session text
     */
    const getText = () => {
        return disable ? 'Sessão encerrada em' : 'Sessão encerra em'
    }

    return (
        <div>
            <ToastContainer />

            <Body>
                {sessionDTO ?
                    <>
                        <div className={styles.sessionContainer}>
                            <h1>Sessão de votação</h1>

                            <div className={styles.agendaContainer}>
                                <h3>{sessionDTO?.title}</h3>
                                <p>{sessionDTO?.description}</p>
                            </div>

                            <div className={styles.expireSession}>
                                <p>{getText()} {sessionDTO?.end}</p>
                            </div>

                            {!disable &&
                                <>
                                    <form className={styles.formContainer} onSubmit={e => handleSubmit(e)}>
                                        <div className={styles.selectContainer}>
                                            <div
                                                className={`${styles.iconContainer} ${selectedIcon === "negative" ? styles.selectedRed : ""}`}
                                                onClick={() => handleIconClick("negative")}
                                            >
                                                <MdThumbDown className={styles.iconNegative} />

                                                <span>Não</span>
                                            </div>

                                            <div
                                                className={`${styles.iconContainer} ${selectedIcon === "positive" ? styles.selectedGreen : ""}`}
                                                onClick={() => handleIconClick("positive")}
                                            >
                                                <MdThumbUp className={styles.iconPositive} />

                                                <span>Sim</span>
                                            </div>
                                        </div>

                                        <Input label="CPF" name="cpf" maxLength={11} type="number" />

                                        <Button label="Votar" />
                                    </form>
                                </>
                            }
                        </div>
                    </>
                    :
                    <>
                        <NoData
                            title="Votação não encontrada"
                            message="Contate um administrador para informar a seção de votação correta!"
                        />
                    </>
                }

            </Body>
        </div>
    );
};

export default Session;
