package aiss.grupo6.vimeoMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptionData {

    @JsonProperty("data")
    private List<Caption> data;

    @JsonProperty("data")
    public List<Caption> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Caption> data) {
        this.data = data;
    }
}
