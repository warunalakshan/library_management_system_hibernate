package util;

import java.time.LocalDate;
import java.util.Date;

public class ReturnDetailsTM {
    private String issueID;
    private String memberID;
    private String bookID;
    private String bookName;
    private Date issueDate;
    private LocalDate returnDate;
    private long lateDays;

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public ReturnDetailsTM(String issueID, String memberID, String bookID, String bookName, Date issueDate, LocalDate returnDate, long lateDays) {
        this.issueID = issueID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.bookName = bookName;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.lateDays = lateDays;
    }

    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public long getLateDays() {
        return lateDays;
    }

    public void setLateDays(long lateDays) {
        this.lateDays = lateDays;
    }

    @Override
    public String toString() {
        return issueID;
    }
}
