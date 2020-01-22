package id.aldiansyah.moviecataloguelocalstorage.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tbTvShow")
public class ResultTvShow implements Parcelable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private Double vote_average;
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String poster;
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdrop;
    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    private String first_air_date;
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    private String popularity;
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    private int vote_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public ResultTvShow() {

    }

    protected ResultTvShow(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        if (in.readByte() == 0) {
            this.vote_average = null;
        } else {
            this.vote_average = in.readDouble();
        }
        this.poster = in.readString();
        this.backdrop = in.readString();
        this.first_air_date = in.readString();
        this.overview = in.readString();
        this.popularity = in.readString();
        this.vote_count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        if (this.vote_average == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(this.vote_average);
        }
        dest.writeString(this.poster);
        dest.writeString(this.backdrop);
        dest.writeString(this.first_air_date);
        dest.writeString(this.overview);
        dest.writeString(this.popularity);
        dest.writeInt(this.vote_count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultTvShow> CREATOR = new Creator<ResultTvShow>() {
        @Override
        public ResultTvShow createFromParcel(Parcel in) {
            return new ResultTvShow(in);
        }

        @Override
        public ResultTvShow[] newArray(int size) {
            return new ResultTvShow[size];
        }
    };
}
