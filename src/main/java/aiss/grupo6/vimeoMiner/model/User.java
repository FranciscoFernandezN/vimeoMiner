
package aiss.grupo6.vimeoMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("user_link")
    private String userLink;

    @JsonProperty("pictures")
    private Map<String, Object> pictures;

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("user_link")
    public String getUserLink() {
        return userLink;
    }

    @JsonProperty("link")
    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    @JsonProperty("pictures")
    public Map<String, Object> getPictures() {
        return pictures;
    }

    @JsonProperty("pictures")
    public void setPictures(Map<String, Object> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("uri");
        sb.append('=');
        sb.append(((this.uri == null)?"<null>":this.uri));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("userLink");
        sb.append('=');
        sb.append(((this.userLink == null)?"<null>":this.userLink));
        sb.append(',');
        sb.append("pictures");
        sb.append('=');
        sb.append(((this.pictures == null)?"<null>":this.pictures));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
