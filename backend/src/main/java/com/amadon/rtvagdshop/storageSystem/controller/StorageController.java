package com.amadon.rtvagdshop.storageSystem.controller;

import com.amadon.rtvagdshop.storageSystem.service.StorageImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/storage" )
public class StorageController
{
    private final StorageImagesService imagesService;

    @GetMapping( value = "/images/{objectName}", consumes = MediaType.ALL_VALUE )
    public ResponseEntity< byte[] > getImage( @PathVariable( "objectName" ) final String aObjectName )
    {
        return imagesService.getImageResponse( aObjectName );
    }
}
