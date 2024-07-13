import { ReactNode } from 'react'
import styles from './body.module.scss'

type BodyProps = {
    children: ReactNode;
}
const Body = ({ children }: BodyProps) => {
    return (
        <div className={styles.bodyContainer}>
            {children}
        </div>
    )
}

export default Body