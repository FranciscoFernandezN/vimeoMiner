package aiss.grupo6.vimeoMiner.service;

import aiss.grupo6.vimeoMiner.database.VMVideo;
import aiss.grupo6.vimeoMiner.model.Video;
import aiss.grupo6.vimeoMiner.model.VideoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public List<VMVideo> indexVideosById(String idChannel, Integer maxVideos) throws RestClientException {
        List<VMVideo> result = new ArrayList<>();
        maxVideos = maxVideos == null? 10: maxVideos;

        String uri = path + idChannel + "/videos?per_page=" + maxVideos;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + token);
        HttpEntity<VideoData> request = new HttpEntity<>(null, headers);

        ResponseEntity<VideoData> response = restTemplate.exchange(uri, HttpMethod.GET, request, VideoData.class);
        for(Video v: response.getBody().getData()) {
            result.add(VMVideo.of(v));
        }

        return result;

    }
}
