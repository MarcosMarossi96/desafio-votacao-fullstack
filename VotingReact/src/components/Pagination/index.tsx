import styles from "./pagination.module.scss";

type PaginationProps = {
    currentPage: number;
    totalPages: number;
    onPageChange: (page: number) => void;
}

const Pagination = ({ currentPage, totalPages, onPageChange }: PaginationProps) => {
    const pages = []

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