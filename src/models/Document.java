package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Document {
    protected int id;
    protected String title;
    protected String author;
    protected String language;
    protected String publicationYear;
    protected int quantity;


    protected ImageView ImageLink;



    protected Document(int id, String title, String author, String language, String publicationYear, String description, int quantity) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
        this.publicationYear = publicationYear;

    }


    protected Document(String title, String author, String language, String publicationYear, String description, int quantity) {

        this.title = title;
        this.author = author;
        this.language = language;
        this.publicationYear = publicationYear;
    }

    public Document(int id, String title) {
        this.id = id;
        this.title = title;

    }

    public void setImageLink(ImageView imageLink) {
        this.ImageLink = imageLink;
    }
    public ImageView getImageLink() {
        return this.ImageLink;
    }

    protected Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Image getImage() {
        return this.ImageLink.getImage();
    }
    public void setImage(Image image) {
        this.ImageLink.setImage(image);
    }
}
