package br.postly.onboarding.domain.model;

import br.postly.auth.domain.model.User;
import br.postly.onboarding.domain.enums.MainGoal;
import br.postly.onboarding.domain.enums.OnboardingStatus;
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
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_creator_profile")
public class CreatorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "ig_username")
    private String igUsername;

    private String niche;

    @Column(name = "main_goal")
    private MainGoal mainGoal;

    @Column(name = "post_per_week")
    private Integer postPerWeek;

    @Column(name = "ig_references")
    private Set<String> igReferences;

    private OnboardingStatus status;

    private String comments;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}