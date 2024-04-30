package aiss.grupo6.vimeoMiner.database;

import aiss.grupo6.vimeoMiner.model.Caption;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Caption")
public class VMCaption {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("language")
    private String language;

    public VMCaption(String id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public static VMCaption of(String id, String name, String language) {
        return new VMCaption(id, name, language);
    }

    public static VMCaption of(Caption c) {
        String id = c.getUri().substring(c.getUri().lastIndexOf("/") + 1);
        return of(id, c.getName(), c.getLanguage());
    }

    public VMCaption() {}

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
