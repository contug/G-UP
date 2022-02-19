package it.unimib.gup.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    private String name;
    private String color;

    public Category() {
        // For JSON mapping
    }

    public Category(String name) {
        this.name = name;
        if(this.name.equals("Natural Sciences"))
            this.color = "#22c55e";
        if(this.name.equals("Humanities"))
            this.color = "#0ea5e9";
        if(this.name.equals("Formal Sciences"))
            this.color = "#6366f1";
        if(this.name.equals("Professions"))
            this.color = "#f59e0b";
        if(this.name.equals("Social Sciences"))
            this.color = "#ec4899";
        if(this.name.equals("Others"))
            this.color = "#ef4444";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeString(this.name);
        dest.writeString(this.color);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.color = source.readString();
    }

    protected Category(Parcel in) {
        this.name = in.readString();
        this.color = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
