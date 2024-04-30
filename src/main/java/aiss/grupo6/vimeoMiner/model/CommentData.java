package aiss.grupo6.vimeoMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentData {
    @JsonProperty("data")
    private List<Comment> data;

    @JsonProperty("data")
    public List<Comment> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Comment> data) {
        this.data = data;
    }
}
