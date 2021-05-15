package group4.musicproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Banner implements  Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("NoiDung")
    @Expose
    private String noiDung;
    @SerializedName("idBaiHat")
    @Expose
    private Integer idBaiHat;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Integer getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(Integer idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }
}