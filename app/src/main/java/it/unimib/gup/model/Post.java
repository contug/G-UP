package it.unimib.gup.model;

public class Post {
    private String id;
    private String authorId;
    private String authorName;
    private String author;
    private String text;
    private String data;

    public Post() {

    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", authorName='" + authorName + '\'' +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Post(String author, String text, String data) {
        this.author = author;
        this.text = text;
        this.data = data;
    }
}


