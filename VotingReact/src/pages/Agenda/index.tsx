import { useEffect, useState } from "react";
import api from "../../services";
import styles from "./agenda.module.scss"
import { AgendaResponse } from "../../TS/agenda-interfaces";
import Pagination from "../../components/Pagination";
import Button from "../../components/Button";
import { useNavigate } from "react-router-dom";
import Header from "../../components/Header";
import Body from "../../components/Body";
import moment from "moment";
import { RequestSession, ResponseSession } from "../../TS/session-interfaces";
import NoData from "../../components/NoData";
import { AxiosError } from "axios";
import { ApiErrorMessage } from "../../TS/error-interfaces";
import { ToastContainer, toast } from 'react-toastify';
import Votes from "../../components/Votes";

/**
 * @description Page with a list of all agendas.
 * @returns {JSX.Element}
 */
const Agenda = () => {
    const [agendas, setAgendas] = useState<AgendaResponse>();
    const [currentPage, setCurrentPage] = useState(0);

    const navigate = useNavigate();

    useEffect(() => {
        /**
         * Fetch all available agendas from the API.
         */
        api.get<AgendaResponse>('/agenda', {
            params: {
                page: currentPage,
                limit: 10,
            },
        })
            .then(res => {
                setAgendas(res.data)
            })
            .catch((error: AxiosError<ApiErrorMessage>) => {
                const message = error?.response?.data.message || 'Ocorreu um erro no processamento!'
                toast(message, {
                    type: 'error',
                })
            })
    }, [currentPage]);

    /**
     * @description Change the page to the one selected by the user.
     * @param page New page
     */
    const handlePageChange = (page: number) => {
        setCurrentPage(page);
    }

    /**
     * @description Create a new voting session and redirect the user to it.
     * @param id Session identifier
     */
    const handleSession = (id: number) => {
        const initialDate = moment(new Date()).format('YYYY-MM-DD HH:mm:ss');
        const finalDate = moment(new Date()).add(2, 'minutes').format('YYYY-MM-DD HH:mm:ss');

        const requestBody: RequestSession = {
            start: initialDate,
            end: finalDate,
            agendaId: id,
        }

        /**
         * Register a new voting session in the API.
         */
        api.post<ResponseSession>('/session', requestBody)
            .then((res) => {
                toast("Sessão de votação criada com sucesso!", {
                    type: 'success',
                })

                const url = `/sessao/${res.data.id}`;
                window.open(url, '_blank');
            })
            .catch((error: AxiosError<ApiErrorMessage>) => {
                const message = error?.response?.data.message || 'Ocorreu um erro no processamento!'
                toast(message, {
                    type: 'error',
                })
            })
    }

    return (
        <div className={styles.agendaContainer}>

            <Header pageTitle="Pautas">
                <Button label="Criar nova pauta" onClick={() => navigate("/nova-pauta")} />
            </Header>

            <ToastContainer />

            <Body>
                {agendas?._embedded?.agendaDTOList && agendas?.page.totalElements > 0 ?
                    <>
                        {agendas._embedded.agendaDTOList.map((item) => (
                            <div key={item.id} className={styles.agendaContent}>
                                <h3>{item.title}</h3>

                                <p>{item.description}</p>

                                {item.vote ?
                                    <>
                                        <Votes noVote={item.vote.noVote} yesVote={item.vote.yesVote} />
                                    </>
                                    :
                                    <>
                                        <Button label="Criar sessão de votação" onClick={() => handleSession(item.id)} />
                                    </>
                                }
                            </div>
                        ))}

                        <div className="paginationContainer">
                            <Pagination
                                currentPage={currentPage}
                                totalPages={agendas?.page.totalPages || 0}
                                onPageChange={handlePageChange}
                            />
                        </div>
                    </>
                    :
                    <>
                        <NoData message="Para conter informações de pauta clique em 'Criar nova pauta'" />
                    </>
                }


            </Body>
        </div>
    )
}

export default Agenda
