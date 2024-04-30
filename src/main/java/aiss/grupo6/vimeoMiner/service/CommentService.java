package aiss.grupo6.vimeoMiner.service;

import aiss.grupo6.vimeoMiner.database.VMComment;
import aiss.grupo6.vimeoMiner.model.Comment;
import aiss.grupo6.vimeoMiner.model.CommentData;
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
public class CommentService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public List<VMComment> indexCommentsByVideoId(String idChannel, String idVideo, Integer maxComments) throws RestClientException {
        List<VMComment> result = new ArrayList<>();
        maxComments = maxComments == null? 10: maxComments;

        String uri = path + idChannel + "/videos/" + idVideo + "/comments?per_page=" + maxComments;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + token);
        HttpEntity<CommentData> request = new HttpEntity<>(null, headers);

        ResponseEntity<CommentData> response = restTemplate.exchange(uri, HttpMethod.GET, request, CommentData.class);
        for(Comment c: response.getBody().getData()) {
            result.add(VMComment.of(c));
        }

        return result;
    }
}
