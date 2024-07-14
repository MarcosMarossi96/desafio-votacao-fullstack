import { useNavigate } from "react-router-dom"
import AssociateIcon from "../../assets/associate-icon"
import Button from "../../components/Button"
import styles from "./associate.module.scss"
import Header from "../../components/Header"
import Body from "../../components/Body"
import { AssociateResponse } from "../../TS/associate-interfaces"
import api from "../../services"
import { useEffect, useState } from "react"
import Pagination from "../../components/Pagination"
import NoData from "../../components/NoData"
import { AxiosError } from "axios"
import { ApiErrorMessage } from "../../TS/error-interfaces"
import { ToastContainer, toast } from "react-toastify";

/**
 * @description Page with a list of all associates
 * @returns {JSX.Element}
 */
const Associate = () => {
    const [associates, setAssociates] = useState<AssociateResponse>();
    const [currentPage, setCurrentPage] = useState(0);

    const navigate = useNavigate();

    useEffect(() => {
        /**
         * Fetch all available associates from the API.
         */
        api.get<AssociateResponse>("/associate", {
            params: {
                page: currentPage,
                limit: 10,
            },
        })
            .then(res => {
                setAssociates(res.data)
            })
            .catch((error: AxiosError<ApiErrorMessage>) => {
                const message = error?.response?.data.message || "Ocorreu um erro no processamento!"
                toast(message, {
                    type: "error",
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

    return (
        <div>
            <Header pageTitle="Associados">
                <Button label="Inserir associado" onClick={() => navigate("/novo-associado")} />
            </Header>

            <ToastContainer />

            <Body>
                {associates?._embedded?.associateDTOList && associates?.page.totalElements > 0 ?
                    <>
                        {associates?._embedded.associateDTOList.map(item => (
                            <div key={item.id} className={styles.associateContent}>
                                <AssociateIcon height="48px" width="48px" />
                                <span>{item.name}, CPF nº</span>
                                <span>{item.cpf}</span>
                            </div>
                        ))}

                        <div>
                            <Pagination
                                currentPage={currentPage}
                                totalPages={associates?.page.totalPages || 0}
                                onPageChange={handlePageChange}
                            />
                        </div>
                    </>
                    :
                    <>
                        <NoData message="Para conter informações de colaboradores no sistema clique no botão de 'Inserir associado'" />
                    </>
                }
            </Body>
        </div>
    )
}

export default Associate