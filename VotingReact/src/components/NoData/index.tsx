import styles from './nodata.module.scss'
import { FaExclamationTriangle } from 'react-icons/fa';

type NoDataProps = {
    message: string;
    title?: string;
}

/**
 * @description Displays a message when no data is available.
 * @param message - The message to display when no data is available.
 * @param title The optional title displayed above the message.
 * @returns {JSX.Element} Returns a React component with an icon, title, and message indicating no data.
 */
const NoData = ({ message, title = 'Nenhum dado foi cadastrado' }: NoDataProps) => {
    return (
        <div className={styles.noDataContainer}>
            <FaExclamationTriangle color="var(--attention-color)" />

            <h3>{title}</h3>
            <p>{message}</p>
        </div>
    )
}

export default NoData