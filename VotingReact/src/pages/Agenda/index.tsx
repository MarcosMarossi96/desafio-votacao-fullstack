import { useEffect, useState } from "react";
import api from "../../services";
import styles from "./agenda.module.scss"
import { AgendaResponse } from "../../TS/agenda-interfaces";
import Pagination from "../../components/Pagination";
import Button from "../../components/Button";
import { useNavigate } from "react-router-dom";
import Header from "../../components/Header";
import Body from "../../components/Body";

const Agenda = () => {
    const [agendas, setAgendas] = useState<AgendaResponse>();
    const navigate = useNavigate();
    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        api.get<AgendaResponse>('/agenda', {
            params: {
                page: currentPage,
                limit: 10,
            },
        }).then(res => {
            setAgendas(res.data)
        })
    }, [currentPage]);

    const handlePageChange = (page: number) => {
        setCurrentPage(page);
    };

    return (
        <div>
            <Header pageTitle="Pautas">
                <Button label="Criar nova pauta" onClick={() => navigate("/nova-pauta")} />
            </Header>

            <Body>
                {agendas?._embedded.agendaDTOList && agendas?._embedded.agendaDTOList.map((item) => (
                    <div key={item.id} className={styles.agendaContent}>
                        <h3>{item.title}</h3>

                        <p>{item.description}</p>

                        <Button label="Criar sessão de votação" />
                    </div>
                ))}

                <div className="paginationContainer">
                    <Pagination
                        currentPage={currentPage}
                        totalPages={agendas?.page.totalPages || 0}
                        onPageChange={handlePageChange}
                    />
                </div>
            </Body>
        </div>
    )
}

export default Agenda