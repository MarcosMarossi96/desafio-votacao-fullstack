import { useNavigate } from "react-router-dom"
import Button from "../../../components/Button"
import styles from './new-associate.module.scss'
import { FormEvent } from "react";
import Input from "../../../components/Input";
import api from "../../../services";
import Header from "../../../components/Header";
import Body from "../../../components/Body";

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

        navigate("/associado")
    }

    return (
        <div>
            <Header pageTitle="Novo associado" />

            <Body>
                <form className={styles.formContainer} onSubmit={(e) => handleSubmit(e)}>
                    <Input name="name" label="Nome" required />
                    <Input name="cpf" label="CPF" maxLength={11} required />

                    <Button label="Criar" />
                </form>
            </Body>
        </div>
    )
}

export default NewAssociate