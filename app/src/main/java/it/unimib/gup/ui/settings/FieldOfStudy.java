package it.unimib.gup.ui.settings;

import android.os.Parcel;
import android.os.Parcelable;

public class FieldOfStudy implements Parcelable {
    private String name;
    private String color;
    private Boolean isSelected;

    public FieldOfStudy(String name, String color, Boolean isSelected) {
        this.name = name;
        this.color = color;
        this.isSelected = isSelected;
    }

    public FieldOfStudy(String name, String color) {
        this.name = name;
        this.color = color;
        this.isSelected = false;
    }

    protected FieldOfStudy(Parcel in) {
        name = in.readString();
        color = in.readString();
        byte tmpIsSelected = in.readByte();
        isSelected = tmpIsSelected == 0 ? null : tmpIsSelected == 1;
    }

    public static final Creator<FieldOfStudy> CREATOR = new Creator<FieldOfStudy>() {
        @Override
        public FieldOfStudy createFromParcel(Parcel in) {
            return new FieldOfStudy(in);
        }

        @Override
        public FieldOfStudy[] newArray(int size) {
            return new FieldOfStudy[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(color);
        dest.writeByte((byte) (isSelected == null ? 0 : isSelected ? 1 : 2));
    }
}

