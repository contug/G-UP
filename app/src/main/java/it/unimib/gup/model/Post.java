package it.unimib.gup.model;

public class Post {
    private String id;
    private String author;
    private String text;

    public Post() {

    }

    public Post(String id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }

    public Post(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}


