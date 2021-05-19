package group4.musicproject.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("CaSi")
    @Expose
    private String caSi;
    @SerializedName("LinkBaiHat")
    @Expose
    private String linkBaiHat;
    @SerializedName("idAlbum")
    @Expose
    private Integer idAlbum;
    @SerializedName("idTheLoai")
    @Expose
    private Integer idTheLoai;
    @SerializedName("idPlayList")
    @Expose
    private Integer idPlayList;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("LuotThich")
    @Expose
    private Integer luotThich;

    protected Song(Parcel in) {
        if (in.readByte( ) == 0) {
            id = null;
        } else {
            id = in.readInt( );
        }
        tenBaiHat = in.readString( );
        hinhBaiHat = in.readString( );
        caSi = in.readString( );
        linkBaiHat = in.readString( );
        if (in.readByte( ) == 0) {
            idAlbum = null;
        } else {
            idAlbum = in.readInt( );
        }
        if (in.readByte( ) == 0) {
            idTheLoai = null;
        } else {
            idTheLoai = in.readInt( );
        }
        if (in.readByte( ) == 0) {
            idPlayList = null;
        } else {
            idPlayList = in.readInt( );
        }
        createdAt = in.readString( );
        updatedAt = in.readString( );
        if (in.readByte( ) == 0) {
            luotThich = null;
        } else {
            luotThich = in.readInt( );
        }
    }

    public static final Creator<Song> CREATOR = new Creator<Song>( ) {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public String getLinkBaiHat() {
        return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.linkBaiHat = linkBaiHat;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Integer idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public Integer getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(Integer idPlayList) {
        this.idPlayList = idPlayList;
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

    public Integer getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(Integer luotThich) {
        this.luotThich = luotThich;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(tenBaiHat);
        dest.writeString(hinhBaiHat);
        dest.writeString(caSi);
        dest.writeString(linkBaiHat);
        if (idAlbum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idAlbum);
        }
        if (idTheLoai == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idTheLoai);
        }
        if (idPlayList == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idPlayList);
        }
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        if (luotThich == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(luotThich);
        }
    }
}