package leandrorodrig.com.br.desafioandroid.model;

//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Entity
public class Item {

    public static DiffCallback<Item> DiffCallback = new DiffCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    @SerializedName("id")
    @Expose
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "repo_id")
    private long id;
    @SerializedName("name")
    @Expose
//    @ColumnInfo(name = "repo_name")
    private String name;
//    @SerializedName("full_name")
//    @Expose
//    @ColumnInfo(name = "full_name")
//    private String fullName;
//    @SerializedName("owner")
//    @Expose
//    @ColumnInfo(name = "owner")
//    private Owner owner;
//    @SerializedName("html_url")
//    @Expose
//    @ColumnInfo(name = "html_url")
//    private String htmlUrl;
    @SerializedName("description")
    @Expose
//    @ColumnInfo(name = "repo_description")
    private String description;
//    @SerializedName("url")
//    @Expose
//    @ColumnInfo(name = "url")
//    private String url;
//    @SerializedName("stargazers_count")
//    @Expose
//    @ColumnInfo(name = "stargazers_count")
//    private long stargazersCount;
//    @SerializedName("forks")
//    @Expose
//    @ColumnInfo(name = "forks")
//    private long forks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }

//    public Owner getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }

//    public String getHtmlUrl() {
//        return htmlUrl;
//    }
//
//    public void setHtmlUrl(String htmlUrl) {
//        this.htmlUrl = htmlUrl;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public long getStargazersCount() {
//        return stargazersCount;
//    }
//
//    public void setStargazersCount(long stargazersCount) {
//        this.stargazersCount = stargazersCount;
//    }
//
//    public long getForks() {
//        return forks;
//    }
//
//    public void setForks(long forks) {
//        this.forks = forks;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Item item = (Item) obj;

        return item.getId() == this.getId() && item.name == this.name;
    }
}
