package com.amadon.rtvagdshop.storageSystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageImagesService
{
    private final String imageFolderName = "images/";
    private final StorageSystemService storageSystemService;

    public ResponseEntity< byte[] > getImageResponse( final String aImageName )
    {
        final byte[] image = getImage( aImageName );
        return ResponseEntity.ok()
                .contentType( getImageMediaType( aImageName ) )
                .body( image );
    }

    public byte[] getImage( final String aImageName )
    {
        final InputStream stream = storageSystemService.getStoredFile( imageFolderName.concat( aImageName ) );
        try
        {
            return IOUtils.toByteArray( stream );
        } catch ( IOException aE )
        {
            log.error( "Error during converting image stream to bytes", aE );
            throw new RuntimeException();
        }
    }

    private MediaType getImageMediaType( final String aImageName )
    {
        final String imageName = aImageName.toLowerCase();
        if ( imageName.endsWith( ".png" ) )
        {
            return MediaType.IMAGE_PNG;
        }
        return MediaType.IMAGE_JPEG;
    }
}
