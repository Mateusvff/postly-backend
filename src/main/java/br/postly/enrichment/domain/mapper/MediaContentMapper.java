package br.postly.enrichment.domain.mapper;

import br.postly.enrichment.api.dto.businessdiscovery.MediaRecord;
import br.postly.enrichment.domain.model.IgReference;
import br.postly.enrichment.domain.model.IgReferenceMediaContent;
import org.springframework.stereotype.Component;

@Component
public class MediaContentMapper {

    public IgReferenceMediaContent mapToMediaContent(MediaRecord mediaRecord, IgReference igReference) {
        IgReferenceMediaContent mediaContent = new IgReferenceMediaContent();
        mediaContent.setIgReference(igReference);
        mediaContent.setCaption(mediaRecord.caption());
        mediaContent.setMediaUrl(mediaRecord.mediaUrl());
        mediaContent.setMediaType(mediaRecord.mediaType());
        mediaContent.setMediaProductType(mediaRecord.mediaProductType());
        mediaContent.setLikeCount(mediaRecord.likeCount());
        mediaContent.setCommentsCount(mediaRecord.commentsCount());
        return mediaContent;
    }
}
