import { ReactNode } from 'react'
import styles from './body.module.scss'

type BodyProps = {
    children: ReactNode;
}

/**
 * @description Template for the body of the page to standardize across all areas of the website.
 * @param children A JSX Element, for example: <Body><div> Content </div></Body>
 * @returns {JSX.Element} Page template
 */
const Body = ({ children }: BodyProps) => {
    return (
        <div className={styles.bodyContainer}>
            {children}
        </div>
    )
}

export default Body