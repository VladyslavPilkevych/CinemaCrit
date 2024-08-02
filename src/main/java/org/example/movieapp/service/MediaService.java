package org.example.movieapp.service;

import org.example.movieapp.model.Media;
import org.example.movieapp.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;


    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

}

