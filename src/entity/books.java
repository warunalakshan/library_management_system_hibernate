package entity;

import java.io.Serializable;

public class books implements SuperEntity {
    private String id;
    private String name;
    private String author;
    private int qty;
    private String isbn;

    public books(String string, String rstString, String author, String s, String isbn) {
    }

    public books(String id, String name, String author, int qty, String isbn) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.qty = qty;
        this.isbn = isbn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "books{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", qty=" + qty +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
