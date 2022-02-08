package it.unimib.gup.model;

public class Note {
    private String id;
    private String authorId;
    private String text;

    public Note(String id, String authorId, String text) {
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
}


