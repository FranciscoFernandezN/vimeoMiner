package aiss.grupo6.vimeoMiner.service;

import aiss.grupo6.vimeoMiner.database.VMCaption;
import aiss.grupo6.vimeoMiner.model.Caption;
import aiss.grupo6.vimeoMiner.model.CaptionData;
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
public class CaptionService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public List<VMCaption> indexCaptionsByVideoId(String idChannel, String idVideo) throws RestClientException {
        List<VMCaption> result = new ArrayList<>();

        String uri = path + idChannel + "/videos/" + idVideo + "/texttracks";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + token);
        HttpEntity<CaptionData> request = new HttpEntity<>(null, headers);

        ResponseEntity<CaptionData> response = restTemplate.exchange(uri, HttpMethod.GET, request, CaptionData.class);
        for(Caption c: response.getBody().getData()) {
            result.add(VMCaption.of(c));
        }

        return result;
    }
}
