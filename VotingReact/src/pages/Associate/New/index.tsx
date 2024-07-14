import { useNavigate } from "react-router-dom"
import Button from "../../../components/Button"
import styles from './new-associate.module.scss'
import { FormEvent } from "react";
import Input from "../../../components/Input";
import api from "../../../services";
import Header from "../../../components/Header";
import Body from "../../../components/Body";
import { AxiosError } from "axios";
import { ToastContainer, toast } from 'react-toastify';
import { ApiErrorMessage } from "../../../TS/error-interfaces";


const NewAssociate = () => {
    const navigate = useNavigate();

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        const formData = new FormData(event.currentTarget);
        const requestObject: Record<string, string> = {};

        formData.forEach((value, key) => {
            requestObject[key] = value.toString();
        });

        await api.post('/associate', requestObject)
            .then(() => {
                navigate("/associado")
            })
            .catch((error: AxiosError<ApiErrorMessage>) => {
                const message = error?.response?.data.message || 'Ocorreu um erro no processamento!'
                toast(message, {
                    type: 'error',
                })
            })
    }

    return (
        <div>
            <Header pageTitle="Novo associado" />

            <ToastContainer />

            <Body>
                <form className={styles.formContainer} onSubmit={(e) => handleSubmit(e)}>
                    <Input name="name" label="Nome" required />
                    <Input name="cpf" label="CPF" type="number" minLength={11} maxLength={11} required />

                    <Button label="Criar" />
                </form>
            </Body>
        </div>
    )
}

export default NewAssociate