package util;

public class ShowBooksTM {
    private String id;
    private String name;
    private String author;
    private int qty;
    private String isbn;

    public ShowBooksTM() {
    }

    public ShowBooksTM(String id, String name, String author, int qty, String isbn) {
        this.setId(id);
        this.setName(name);
        this.setAuthor(author);
        this.setQty(qty);
        this.setIsbn(isbn);
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
        return "ShowBooksTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", qty=" + qty +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
