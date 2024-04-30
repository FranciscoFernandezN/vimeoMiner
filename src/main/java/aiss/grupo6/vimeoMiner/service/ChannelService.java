package aiss.grupo6.vimeoMiner.service;

import aiss.grupo6.vimeoMiner.database.VMChannel;
import aiss.grupo6.vimeoMiner.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public VMChannel findChannelById(String idChannel) throws RestClientException {
        VMChannel result = null;
        String uri =  path + idChannel;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + token);
        HttpEntity<Channel> request = new HttpEntity<>(null, headers);

        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.GET, request, Channel.class);
        result = VMChannel.of(response.getBody());

        return result;
    }

}
