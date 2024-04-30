package aiss.grupo6.vimeoMiner.database;

import aiss.grupo6.vimeoMiner.model.Video;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Video")
public class VMVideo {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @NotEmpty(message = "Video name cannot be empty")
    private String name;

    @JsonProperty("description")
    @Column(columnDefinition="TEXT")
    private String description;

    @JsonProperty("releaseTime")
    @NotEmpty(message = "Video release time cannot be empty")
    private String releaseTime;

    @JsonProperty("comments")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    @NotNull(message = "Video comments cannot be null")
    private List<VMComment> comments;

    @JsonProperty("captions")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    @NotNull(message = "Video captions cannot be null")
    private List<VMCaption> captions;

    public VMVideo() {
        this.comments = new ArrayList<>();
        this.captions = new ArrayList<>();
    }

    public VMVideo(String id, String name, String description, String releaseTime, List<VMComment> comments, List<VMCaption> captions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.comments = comments;
        this.captions = captions;
    }

    public static VMVideo of(String id, String name, String description, String releaseTime, List<VMComment> comments, List<VMCaption> captions) {
        return new VMVideo(id, name, description, releaseTime, comments, captions);
    }

    public static VMVideo of(Video v) {
        String id = v.getUri().substring(v.getUri().lastIndexOf("/") + 1);
        return of(id, v.getName(), v.getDescription(), v.getReleaseTime(), new ArrayList<>(), new ArrayList<>());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<VMComment> getComments() {
        return comments;
    }

    public void setComments(List<VMComment> comments) {
        this.comments = comments;
    }

    public List<VMCaption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<VMCaption> captions) {
        this.captions = captions;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", comments=" + comments +
                ", captions=" + captions +
                '}';
    }
}
