import { ChangeEvent, InputHTMLAttributes, useState } from "react";
import styles from './input.module.scss'

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
    label: string;
    name: string;
}

/**
 * @description Custom input component
 * @param label Text to identificate field (label)
 * @param rest Default input Props
 * @returns {JSX.Element} HTML input
 */
const Input = ({ label, name, ...rest }: InputProps) => {
    const [value, setValue] = useState<string>('');

    /**
     * @description When the input is numeric-only, this function limits the number of digits.
     * @param event Input event
     */
    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { value: inputValue } = event.target;

        if (rest.maxLength && rest.type == 'number') {
            const numericValue = inputValue.replace(/\D/g, '');

            const truncatedValue = numericValue.slice(0, rest.maxLength);

            if (rest.maxLength >= inputValue.length) {
                setValue(truncatedValue);
            }
        } else {
            setValue(inputValue)
        }
    }

    return (
        <div>
            <label htmlFor={name} className={styles.inputLabel}>{label}</label>
            <input
                className={styles.inputField}
                name={name}
                type="text"
                {...rest}
                value={value}
                onChange={e => handleChange(e)}
            />
        </div>
    )
}

export default Input