import styles from "./pagination.module.scss";

type PaginationProps = {
    currentPage: number;
    totalPages: number;
    onPageChange: (page: number) => void;
}

/**
 * @description Data pagination manager.
 * @param currentPage Current page.
 * @param totalPages Total pages than exists in REST API.
 * @param onPageChange Change the current page to the previous or next one.
 * @returns {JSX.Element} Pagination Manager.
 */
const Pagination = ({ currentPage, totalPages, onPageChange }: PaginationProps) => {
    const pages = []

    /**
     * Builds an array of buttons representing pagination pages.
     * Each button is rendered based on the total number of pages and the current page.
     */
    for (let i = 0; i < totalPages; i++) {
        pages.push(
            <button
                key={i}
                className={i === currentPage ? styles.activePage : ''}
                onClick={() => onPageChange(i)}
            >
                {i + 1}
            </button>,
        );
    }

    return (
        <div className={styles.pagination}>
            <button
                onClick={() => onPageChange(currentPage - 1)}
                disabled={currentPage === 0}
            >
                « Anterior
            </button>
            {pages}
            <button
                onClick={() => onPageChange(currentPage + 1)}
                disabled={currentPage === totalPages - 1}
            >
                Próximo »
            </button>
        </div>
    );
};

export default Pagination;