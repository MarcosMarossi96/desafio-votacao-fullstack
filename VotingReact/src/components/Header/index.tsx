import styles from './header.module.scss'
import Menu from '../Menu';
import { ReactNode } from 'react';

type HeaderProps = {
    children?: ReactNode;
    pageTitle: string;
}

const Header = ({ children, pageTitle }: HeaderProps) => {
    return (
        <header>
            <Menu />

            <div className={styles.titleContainer}>
                <h2>{pageTitle}</h2>

                {children}
            </div>
        </header>
    )
}

export default Header