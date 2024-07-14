import styles from './header.module.scss'
import Menu from '../Menu';
import { ReactNode } from 'react';

type HeaderProps = {
    children?: ReactNode;
    pageTitle: string;
}

/**
 * @description Displays a header with a navigation menu and a page title.
 * @param children Optional children element.
 * @param pageTitle The title of the page displayed in the header.
 * @returns {JSX.Element} Returns a React component representing a header with navigation and a title.
 */
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