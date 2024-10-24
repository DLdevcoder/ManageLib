//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package models;

public class Book extends Document {
    protected String publisher;
    protected String isbn;
    protected int categoryId;

    protected String googleId;
    protected String thumbnail;

    public Book(int bookId, String title, String author, String publisher, int year, String isbn, int quantity, int categoryId, String googleId, String description, String thumbnail, String language) {
        super(bookId, title, author, language, year, description, quantity);
        this.publisher = publisher;
        this.isbn = isbn;
        this.categoryId = categoryId;
        this.googleId = googleId;
        this.thumbnail = thumbnail;
    }

    public Book(String title, String publisher, int year, String isbn, int quantity, String description, String thumbnail, String language) {
        this.setTitle(title);
        this.publisher = publisher;
        this.setPublicationYear(year);
        this.isbn = isbn;
        this.quantity = quantity;
        this.setDescription(description);
        this.thumbnail = thumbnail;
        this.setLanguage(language);
    }

    public Book(int bookId, String title, String publisher, int year, String isbn, int quantity, String description, String thumbnail, String language) {
        this.setId(bookId);
        this.title = title;
        this.publisher = publisher;
        this.setPublicationYear(year);
        this.isbn = isbn;
        this.setDescription(description);
        this.quantity = quantity;
        this.setPublicationYear(publicationYear);
        this.thumbnail = thumbnail;
        this.setLanguage(language);

    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String getInfo() {
        return "";
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}

