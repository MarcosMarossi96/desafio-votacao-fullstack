import { TextareaHTMLAttributes } from "react";
import styles from './textarea.module.scss'

interface TextProps extends TextareaHTMLAttributes<HTMLTextAreaElement> {
    label: string;
    name: string;
}

const TextArea = ({ label, name, ...rest }: TextProps) => {
    return (
        <div>
            <label htmlFor={name} className={styles.textAreaLabel}>{label}</label>
            <textarea className={styles.textAreaField} name={name} {...rest} />
        </div>
    )
}

export default TextArea