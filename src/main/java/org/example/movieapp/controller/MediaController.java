package org.example.movieapp.controller;

import org.example.movieapp.model.Media;
import org.example.movieapp.service.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MediaController {

    @Autowired
    private MediaService mediaService;

    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

    @GetMapping("/media")
    @ResponseBody
    public List<Media> showMediaList() {
        List<Media> mediaList = mediaService.getAllMedia();
//        movies.forEach(media -> System.out.println(media));
        return mediaList;
    }


}
