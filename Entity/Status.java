package entity;

/**
 * An enumeration of all the statuses of a suggestion.
 * <ul>
 * <li>PENDING
    <li>APPROVED
    <li>REJECTED
 * </ul>
 * <div>Only staff can approve or reject a suggestion</div>
 */
public enum Status {
    PENDING,
    APPROVED,
    REJECTED
}
