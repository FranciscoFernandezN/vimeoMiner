package aiss.grupo6.vimeoMiner.database;

import aiss.grupo6.vimeoMiner.model.Comment;
import aiss.grupo6.vimeoMiner.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Comment")
public class VMComment {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    @Column(columnDefinition="TEXT")
    private String text;

    @JsonProperty("createdOn")
    private String createdOn;

    @JsonProperty("author")
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Comment author cannot be null")
    private VMUser author;

    public VMComment(String id, String text, String createdOn, VMUser author) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
        this.author = author;
    }

    public static VMComment of(String id, String text, String createdOn, VMUser author) {
        return new VMComment(id, text, createdOn, author);
    }

    public static VMComment of(Comment c) {
        String id = c.getUri().substring(c.getUri().lastIndexOf("/") + 1);
        VMUser author = VMUser.of(c.getAuthor());
        return of(id, c.getText(), c.getCreatedOn(), author);
    }

    public VMComment() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public VMUser getAuthor() {
        return author;
    }

    public void setAuthor(VMUser author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", author=" + author +
                '}';
    }
}
