package com.amadon.rtvagdshop.configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StorageSystemConfiguration
{
    private final String storageHost;
    private final String storageUser;
    private final String storagePassword;
    private final String storageBaseBucket;

    StorageSystemConfiguration( @Value( "${amazon.s3.host}" ) final String aHost,
                                @Value( "${amazon.s3.user}" ) final String aUser,
                                @Value( "${amazon.s3.password}" ) final String aPassword,
                                @Value( "${amazon.s3.baseBucketName}" ) final String aBucketName )
    {
        storageHost = aHost;
        storageUser = aUser;
        storagePassword = aPassword;
        storageBaseBucket = aBucketName;
    }

    @Bean
    public MinioClient initializeMinioCLient()
    {
        final MinioClient client = getClient();
        initBucket( client );
        return client;
    }

    private MinioClient getClient()
    {
        return MinioClient.builder()
                .endpoint( storageHost )
                .credentials( storageUser, storagePassword )
                .build();
    }

    private void initBucket( final MinioClient aClient )
    {
        final BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                .bucket( storageBaseBucket )
                .build();
        try
        {
            if ( aClient.bucketExists( bucketExistsArgs ) )
            {
                return;
            }
        } catch ( Exception aE )
        {
            log.error( "Could not initialize default bucket on the checking default bucket existence", aE );
            throw new FatalBeanException( "Failed to check bucket existence", aE );
        }
        final MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                .bucket( storageBaseBucket )
                .build();
        try
        {
            aClient.makeBucket( makeBucketArgs );
        } catch ( Exception aE )
        {
            log.error( "Could not initialize default bucket while trying to create new default bucket", aE );
            throw new FatalBeanException( "Failed to create bucket", aE );
        }

    }
}
