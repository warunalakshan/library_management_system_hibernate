package util;

import java.util.Date;

public class IssueDetailsTM {
private String issueId;
private String memberId;
private String bookId;
private Date date;

    public IssueDetailsTM() {
    }

    public IssueDetailsTM(String issueId, String memberId, String bookId, Date date) {
        this.issueId = issueId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "IssueDetailsTM{" +
                "issueId='" + issueId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", date=" + date +
                '}';
    }
}
