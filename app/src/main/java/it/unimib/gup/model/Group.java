package it.unimib.gup.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Group implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private Category category;
    private HashMap<String, String> members;
    private HashMap<String, Meeting> meetings;
    private HashMap<String, Post> posts;
    private String color;
    private String owner;
    private long createdAt;

    public Group() {
        // For JSON mapping
    }


    public Group(String id, String owner, String name, String description, String imageUrl, Category category, HashMap<String, String> members, HashMap<String, Meeting> meetings, HashMap<String, Post> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.members = members;
        this.meetings = meetings;
        this.posts = posts;
        this.owner = owner;
        this.createdAt = Instant.now().toEpochMilli();

        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", rand_num);



        this.color = colorCode;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int membersCount() {
        if (members == null) {
            return 0;
        }
        return getMembers().size();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public HashMap<String, String> getMembers() {
        return members;
    }

    public void setMembers(HashMap<String, String> members) {
        this.members = members;
    }

    public HashMap<String, Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(HashMap<String, Meeting> meetings) {
        this.meetings = meetings;
    }

    public HashMap<String, Post> getPosts() {
        return posts;
    }

    public void setPosts(HashMap<String, Post> posts) {
        this.posts = posts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
                ", owner='" + owner + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.imageUrl);
        dest.writeParcelable(this.category, flags);
        dest.writeString(this.color);
        dest.writeString(this.owner);
        dest.writeLong(this.createdAt);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.name = source.readString();
        this.description = source.readString();
        this.imageUrl = source.readString();
        this.category = source.readParcelable(Category.class.getClassLoader());
        this.color = source.readString();
        this.owner = source.readString();
        this.createdAt = source.readLong();
    }

    protected Group(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.imageUrl = in.readString();
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.color = in.readString();
        this.owner = in.readString();
        this.createdAt = in.readLong();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
