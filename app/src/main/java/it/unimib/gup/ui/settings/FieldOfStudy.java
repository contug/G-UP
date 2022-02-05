package it.unimib.gup.ui.settings;

import android.os.Parcel;
import android.os.Parcelable;

public class FieldOfStudy implements Parcelable {
    private String name;
    private String color;

    public FieldOfStudy(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "FieldOfStudy{" +
                "name='" + name + '\'' +
                ", color=" + color +
                '}';
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

    protected FieldOfStudy(Parcel in) {
        this.name = in.readString();
        this.color = in.readString();
    }

    public static final Creator<FieldOfStudy> CREATOR = new Creator<FieldOfStudy>() {
        @Override
        public FieldOfStudy createFromParcel(Parcel source) {
            return new FieldOfStudy(source);
        }

        @Override
        public FieldOfStudy[] newArray(int size) {
            return new FieldOfStudy[size];
        }
    };
}
