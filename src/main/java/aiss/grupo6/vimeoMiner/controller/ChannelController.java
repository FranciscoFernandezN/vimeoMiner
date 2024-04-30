package aiss.grupo6.vimeoMiner.controller;

import aiss.grupo6.vimeoMiner.database.*;
import aiss.grupo6.vimeoMiner.exception.ChannelNotFoundException;
import aiss.grupo6.vimeoMiner.model.*;
import aiss.grupo6.vimeoMiner.repository.ChannelRepository;
import aiss.grupo6.vimeoMiner.service.CaptionService;
import aiss.grupo6.vimeoMiner.service.ChannelService;
import aiss.grupo6.vimeoMiner.service.CommentService;
import aiss.grupo6.vimeoMiner.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vimeoMiner")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CaptionService captionService;

    @Autowired
    private CommentService commentService;

    //GET http://localhost:8081/vimeoMiner/{id}
    @GetMapping("/{id}")
    public VMChannel findChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos, @RequestParam(required = false) Integer maxComments) throws ChannelNotFoundException {
        try {
            VMChannel result = this.channelService.findChannelById(id);
            List<VMVideo> videosCanal = this.videoService.indexVideosById(id, maxVideos);
            for (VMVideo v : videosCanal) {
                List<VMCaption> subtitulosVideo = this.captionService.indexCaptionsByVideoId(id, v.getId());
                List<VMComment> comentariosVideo = this.commentService.indexCommentsByVideoId(id, v.getId(), maxComments);

                v.setComments(comentariosVideo);
                v.setCaptions(subtitulosVideo);
            }

            result.setVideos(videosCanal);
            return result;
        } catch(RestClientException e) {
            throw new ChannelNotFoundException();
        }


    }

    //POST http://localhost:8081/vimeoMiner/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel createChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos, @RequestParam(required = false) Integer maxComments) throws ChannelNotFoundException{
        VMChannel canal = findChannel(id, maxVideos, maxComments);
        canal = channelRepository.save(canal);
        return canal;
    }

}
