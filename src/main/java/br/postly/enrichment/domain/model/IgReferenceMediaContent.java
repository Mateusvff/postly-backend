package br.postly.enrichment.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_ig_media_content")
public class IgReferenceMediaContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private IgReference igReference;

    private String caption;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "media_product_type")
    private String mediaProductType;

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "comments_count")
    private Integer commentsCount;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
