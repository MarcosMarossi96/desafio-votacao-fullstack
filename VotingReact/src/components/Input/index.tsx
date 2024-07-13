import { InputHTMLAttributes } from "react";
import styles from './input.module.scss'

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
    label: string;
    name: string;
}

const Input = ({ label, name, ...rest }: InputProps) => {
    return (
        <div>
            <label htmlFor={name} className={styles.inputLabel}>{label}</label>
            <input className={styles.inputField} name={name} type="text" {...rest} />
        </div>
    )
}

export default Input