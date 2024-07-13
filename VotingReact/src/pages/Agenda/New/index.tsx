import { useNavigate } from "react-router-dom"
import Button from "../../../components/Button"
import styles from './new-agenda.module.scss'
import { FormEvent } from "react";
import Input from "../../../components/Input";
import TextArea from "../../../components/TextArea";
import api from "../../../services";
import Header from "../../../components/Header";
import Body from "../../../components/Body";

const NewAgenda = () => {
    const navigate = useNavigate();

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        const formData = new FormData(event.currentTarget);
        const requestObject: Record<string, string> = {};

        formData.forEach((value, key) => {
            requestObject[key] = value.toString();
        });

        await api.post('/agenda', requestObject)

        navigate("/pauta")
    }

    return (
        <div>
            <Header pageTitle="Nova pauta" />

            <Body>
                <form className={styles.formContainer} onSubmit={(e) => handleSubmit(e)}>
                    <Input name="title" label="Título" required />
                    <TextArea name="description" label="Descrição" required></TextArea>

                    <Button label="Criar" />
                </form>
            </Body>
        </div>
    )
}

export default NewAgenda