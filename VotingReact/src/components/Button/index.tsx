import { ButtonHTMLAttributes } from 'react';
import styles from './button.module.scss'

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
    label: string;
}

const Button = ({ label, ...rest }: ButtonProps) => {
    return (
        <button className={styles.button} {...rest}>
            <span>{label}</span>
        </button>
    )
}

export default Button;