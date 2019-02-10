package com.gramcaster.radinaldn.aurelia.model;

/**
 * Created by radinaldn on 10/02/19.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    public static final String ID = "id";
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("users_id")
    @Expose
    private Integer usersId;
    @SerializedName("username_ig")
    @Expose
    private String usernameIg;
    @SerializedName("id_paket")
    @Expose
    private Integer idPaket;
    @SerializedName("biaya")
    @Expose
    private Integer biaya;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("tanggal_expired")
    @Expose
    private String tanggalExpired;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getUsernameIg() {
        return usernameIg;
    }

    public void setUsernameIg(String usernameIg) {
        this.usernameIg = usernameIg;
    }

    public Integer getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Integer idPaket) {
        this.idPaket = idPaket;
    }

    public Integer getBiaya() {
        return biaya;
    }

    public void setBiaya(Integer biaya) {
        this.biaya = biaya;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTanggalExpired() {
        return tanggalExpired;
    }

    public void setTanggalExpired(String tanggalExpired) {
        this.tanggalExpired = tanggalExpired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}