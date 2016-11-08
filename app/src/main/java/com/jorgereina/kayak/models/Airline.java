package com.jorgereina.kayak.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airline implements Parcelable{

    @SerializedName("__clazz")
    @Expose
    public String clazz;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("defaultName")
    @Expose
    public String defaultName;
    @SerializedName("logoURL")
    @Expose
    public String logoURL;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("site")
    @Expose
    public String site;
    @SerializedName("usName")
    @Expose
    public String usName;


    protected Airline(Parcel in) {
        clazz = in.readString();
        code = in.readString();
        defaultName = in.readString();
        logoURL = in.readString();
        name = in.readString();
        phone = in.readString();
        site = in.readString();
        usName = in.readString();
    }

    public static final Creator<Airline> CREATOR = new Creator<Airline>() {
        @Override
        public Airline createFromParcel(Parcel in) {
            return new Airline(in);
        }

        @Override
        public Airline[] newArray(int size) {
            return new Airline[size];
        }
    };

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(clazz);
        parcel.writeString(code);
        parcel.writeString(defaultName);
        parcel.writeString(logoURL);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(site);
        parcel.writeString(usName);
    }

}
