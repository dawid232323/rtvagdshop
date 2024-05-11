package com.amadon.rtvagdshop.storageSystem.service;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
class StorageSystemService
{
    private final MinioClient minioClient;
    private final String baseBucketName;

    StorageSystemService( final MinioClient aClient, @Value( "${amazon.s3.baseBucketName}" ) final String aBucketName )
    {
        minioClient = aClient;
        baseBucketName = aBucketName;
    }

    public InputStream getStoredFile( final String aObjectName )
    {
        final GetObjectArgs getObjectArgs = getObjectArgs( aObjectName );
        InputStream stream;
        try
        {
            stream = minioClient.getObject( getObjectArgs );
        } catch ( Exception aE )
        {
            log.error( "Could not retrieve object {}", aObjectName, aE );
            throw new RuntimeException();
        }
        return stream;
    }

    private GetObjectArgs getObjectArgs( final String aObjectName )
    {
        return GetObjectArgs.builder()
                .bucket( baseBucketName )
                .object( aObjectName )
                .build();
    }

}
