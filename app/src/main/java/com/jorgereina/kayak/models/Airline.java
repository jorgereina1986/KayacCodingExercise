package com.jorgereina.kayak.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airline{

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

    public Airline(String name, String logoURL) {
        this.name = name;
        this.logoURL = logoURL;
    }

    public Airline() {
    }

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


}
