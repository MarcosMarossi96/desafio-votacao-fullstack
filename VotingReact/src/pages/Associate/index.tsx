import { useNavigate } from "react-router-dom"
import AssociateIcon from "../../assets/associate-icon"
import Button from "../../components/Button"
import styles from './associate.module.scss'
import Header from "../../components/Header"
import Body from "../../components/Body"
import { AssociateResponse } from "../../TS/associate-interfaces"
import api from "../../services"
import { useEffect, useState } from "react"
import Pagination from "../../components/Pagination"

const Associate = () => {
    const navigate = useNavigate();
    const [associates, setAssociates] = useState<AssociateResponse>();
    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        api.get<AssociateResponse>('/associate', {
            params: {
                page: currentPage,
                limit: 10,
            },
        }).then(res => {
            setAssociates(res.data)
        })
    }, [currentPage]);

    const handlePageChange = (page: number) => {
        setCurrentPage(page);
    };

    return (
        <div>
            <Header pageTitle="Associados">
                <Button label="Inserir associado" onClick={() => navigate("/novo-associado")} />
            </Header>

            <Body>

                {associates?._embedded.associateDTOList.map(item => (
                    <div key={item.id} className={styles.associateContent}>
                        <AssociateIcon height="48px" width="48px" />
                        <span>{item.name}, nº</span>
                        <span>{item.cpf}</span>
                    </div>
                ))}

                <div className="paginationContainer">
                    <Pagination
                        currentPage={currentPage}
                        totalPages={associates?.page.totalPages || 0}
                        onPageChange={handlePageChange}
                    />
                </div>
            </Body>
        </div>
    )
}

export default Associate