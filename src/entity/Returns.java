package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Returns implements SuperEntity {
    private String issueId;
    private Date returnDate;
    private BigDecimal fee;

    public Returns() {
    }

    public Returns(String issueId, Date returnDate, BigDecimal fee) {
        this.issueId = issueId;
        this.returnDate = returnDate;
        this.fee = fee;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return issueId;
    }
}
