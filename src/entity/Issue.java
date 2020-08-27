package entity;

import java.util.Date;


public class Issue implements SuperEntity {
    private String issueId;
    private String memberId;
    private String bookId;
    private java.sql.Date issueDate;

    public Issue() {
    }

    public Issue(String issueId, String memberId, String bookId, java.sql.Date issueDate) {
        this.issueId = issueId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(java.sql.Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return issueId;
    }
}
