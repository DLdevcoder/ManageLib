package models;

public class Theses extends Document{
    private String degree;
    private String institution;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Theses(int id, String title, String author, String language, int publicationYear,
                  String description, int quantity, String degree, String institution) {
        super(id, title, author, language, publicationYear, description, quantity);
        this.degree = degree;
        this.institution = institution;
    }

    public Theses(String title, String author, String language, int publicationYear,
                  String description, int quantity, String degree, String institution) {
        super(title, author, language, publicationYear, description, quantity);
        this.degree = degree;
        this.institution = institution;
    }

    @Override
    public String getInfo() {
        return "";
    }

}
