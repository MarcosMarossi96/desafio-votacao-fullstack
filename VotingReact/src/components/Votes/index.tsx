import { MdThumbDown, MdThumbUp } from "react-icons/md"
import styles from './votes.module.scss'

type VoteProps = {
    yesVote: number;
    noVote: number;
}

/**
 * @description Displays voting statistics with thumbs-up and thumbs-down icons.
 * @param yesVote The number of 'yes' votes.
 * @param noVote The number of 'no' votes.
 * @returns {JSX.Element} Returns a React component showing voting statistics with icons.
 */
const Votes = ({ noVote, yesVote }: VoteProps) => {

    return (
        <div className={styles.voteContainer}>
            <div className={`${styles.baseContainer} ${styles.positiveContainer}`}>
                <MdThumbUp />
                <span>{yesVote}</span>
            </div>

            <div className={`${styles.baseContainer} ${styles.negativeContainer}`}>
                <MdThumbDown />
                <span>{noVote}</span>
            </div>
        </div>
    )
}

export default Votes