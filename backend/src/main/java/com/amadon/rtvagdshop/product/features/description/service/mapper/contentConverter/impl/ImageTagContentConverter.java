package com.amadon.rtvagdshop.product.features.description.service.mapper.contentConverter.impl;

import com.amadon.rtvagdshop.product.features.description.service.mapper.contentConverter.ProductDescriptionContentConverter;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Converter that takes all image tags and adds appropriate protocol and host type for the web client proper image
 * display.
 */
@Component
public class ImageTagContentConverter implements ProductDescriptionContentConverter
{

    private final String protocol;
    private final String hostName;
    private final String defaultImagePath = "images/default";
    private final String imageSrcAttribute = "src";

    ImageTagContentConverter( @Value( "${shop.host.protocol}" ) final String aProtocol,
                              @Value( "${shop.host.name}" ) final String aHostName )
    {
        protocol = aProtocol;
        hostName = aHostName;
    }

    @Override
    public void convertContent( final Document parsedContent )
    {
        final Elements imageTags = getAllImageTags( parsedContent );
        changeProtocolInImages( imageTags );
    }

    private Elements getAllImageTags( final Document aDocument )
    {
        final String imageTagName = "img";
        return aDocument.getElementsByTag( imageTagName );
    }

    private void changeProtocolInImages( final Elements aImageTags )
    {
        aImageTags.forEach( this::updateOrAddSrcAttribute );
    }

    private void updateOrAddSrcAttribute( final Element aImageTag )
    {
        final Attribute srcAttribute = aImageTag.attribute( imageSrcAttribute );
        if ( Objects.isNull( srcAttribute ) )
        {
            addSrcAttribute( aImageTag );
        } else {
            updateSrcAttribute( srcAttribute );
        }
    }

    private void updateSrcAttribute( final Attribute aAttribute )
    {
        final String existingValue = aAttribute.getValue();
        aAttribute.setValue( getProtocolWithHost().concat( existingValue ) );
    }

    private void addSrcAttribute( final Element aImageTag )
    {
        aImageTag.attr( imageSrcAttribute, defaultImagePath );
    }

    private String getProtocolWithHost()
    {
        return String.format( "%s%S", protocol, hostName );
    }

}
