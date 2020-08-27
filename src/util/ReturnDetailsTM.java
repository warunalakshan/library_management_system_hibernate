package util;

import java.time.LocalDate;
import java.util.Date;

public class ReturnDetailsTM {
    private String issueID;
    private String memberID;
    private String bookID;
    private String bookName;
    private Date returnDate;
    private int lateDays;
    private double fee;


    public ReturnDetailsTM() {
    }

    public ReturnDetailsTM(String issueID, String memberID, String bookID, String bookName, Date returnDate, int lateDays, double fee) {
        this.issueID = issueID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.bookName = bookName;
        this.returnDate = returnDate;
        this.lateDays = lateDays;
        this.fee = fee;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getLateDays() {
        return lateDays;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    @Override
    public String toString() {
        return issueID;
    }
}
