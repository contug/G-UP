package it.unimib.gup.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Random;

public class Group implements Parcelable {

    private String id;
    private String name;
    private String description;
    private Category category;
    private List<String> members;
    private List<Meeting> meetings;
    private List<Post> posts;
    private String color;

    public Group() {
        // For JSON mapping
    }

    public Group(String id, String name, String description, Category category, List<String> members, List<Meeting> meetings, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.members = members;
        this.meetings = meetings;
        this.posts = posts;

        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", rand_num);

        this.color = colorCode;
    }

    protected Group(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        members = in.createStringArrayList();
        color = in.readString();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public List<Post> getNotes() {
        return posts;
    }

    public void setNotes(List<Post> posts) {
        this.posts = posts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeParcelable(category, flags);
        dest.writeStringList(members);
        dest.writeString(color);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", members=" + members +
                ", meetings=" + meetings +
                ", posts=" + posts +
                ", color='" + color + '\'' +
                '}';
    }
}
