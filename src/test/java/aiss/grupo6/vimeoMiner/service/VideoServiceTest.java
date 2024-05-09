package aiss.grupo6.vimeoMiner.service;

import aiss.grupo6.vimeoMiner.database.VMVideo;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class VideoServiceTest {

    @Autowired
    VideoService service;

    @Test
    @DisplayName("Find videos by id with default maxVideos parameter and without comments and captions")
    public void indexVideosByIdAndNull() {
        List<VMVideo> videos = service.indexVideosById("12346",null);
        assertTrue(videos.size() <= 10, "Default size of the videos must be 10 or lower");
        assertNotNull(videos, "Video list must not be null");
        if(!videos.isEmpty()) {
            for (VMVideo v : videos) {
                assertTrue(v.getComments().isEmpty(), "Comments must be empty");
                assertTrue(v.getCaptions().isEmpty(), "Captions must be empty");
                assertInstanceOf(String.class, v.getId(), "Id must be a string");
                assertInstanceOf(String.class, v.getName(), "Name must be a string");
                assertInstanceOf(String.class, v.getDescription(), "Description must be a string");
                assertInstanceOf(String.class, v.getReleaseTime(), "Release time must be a string");
            }
        }
    }

    @Test
    @DisplayName("Find videos by id without comments and captions")
    public void indexVideosById() {
        int maxV = 5;
        List<VMVideo> videos = service.indexVideosById("12346",maxV);
        assertTrue(videos.size() <= maxV, "The size of the videos list must be lower or equal to " + maxV);
        assertNotNull(videos, "Video list must not be null");
        if(!videos.isEmpty()) {
            for (VMVideo v : videos) {
                assertTrue(v.getComments().isEmpty(), "Comments must be empty");
                assertTrue(v.getCaptions().isEmpty(), "Captions must be empty");
                assertInstanceOf(String.class, v.getId(), "Id must be a string");
                assertInstanceOf(String.class, v.getName(), "Name must be a string");
                assertInstanceOf(String.class, v.getDescription(), "Description must be a string");
                assertInstanceOf(String.class, v.getReleaseTime(), "Release time must be a string");
            }
        }
    }

    @Test
    @DisplayName("Not found exception")
    public void invalidChanelId() {
        try {
            List<VMVideo> canalInvalido = service.indexVideosById("12", null);
        } catch(HttpClientErrorException e){
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}