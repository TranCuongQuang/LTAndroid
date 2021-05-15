package group4.musicproject.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicCategory {
    @SerializedName("TheLoai")
    @Expose
    private List<Topic> theLoai = null;
    @SerializedName("ChuDe")
    @Expose
    private List<Category> chuDe = null;

    public List<Topic> getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(List<Topic> theLoai) {
        this.theLoai = theLoai;
    }

    public List<Category> getChuDe() {
        return chuDe;
    }

    public void setChuDe(List<Category> chuDe) {
        this.chuDe = chuDe;
    }
}
