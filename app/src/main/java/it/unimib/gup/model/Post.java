package it.unimib.gup.model;

public class Post {
    private String id;
    private String authorId;
    private String text;

    public Post(String id, String authorId, String text) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
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
                ", authorId='" + authorId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}


