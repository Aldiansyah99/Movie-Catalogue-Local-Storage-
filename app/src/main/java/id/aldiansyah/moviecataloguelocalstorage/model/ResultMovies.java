package id.aldiansyah.moviecataloguelocalstorage.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tbMovies")
public class ResultMovies implements Parcelable {
    @PrimaryKey
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private Double vote_average;
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String poster;
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdrop;
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String release_date;
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    private String popularity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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

    public ResultMovies() {

    }

    protected ResultMovies(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        if (in.readByte() == 0) {
            this.vote_average = null;
        } else {
            this.vote_average = in.readDouble();
        }
        this.poster = in.readString();
        this.backdrop = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.popularity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        if (this.vote_average == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(this.vote_average);
        }
        dest.writeString(this.poster);
        dest.writeString(this.backdrop);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
        dest.writeString(this.popularity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultMovies> CREATOR = new Creator<ResultMovies>() {
        @Override
        public ResultMovies createFromParcel(Parcel in) {
            return new ResultMovies(in);
        }

        @Override
        public ResultMovies[] newArray(int size) {
            return new ResultMovies[size];
        }
    };
}
