package br.postly.onboarding.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainGoal {

    INCREASE_ENGAGEMENT("Increase audience engagement with likes, comments, and shares"),
    ATTRACT_CLIENTS("Attract new clients and qualified leads for the business"),
    EDUCATE("Educate the audience about a specific topic, sharing knowledge and useful information"),
    ENTERTAIN("Entertain the audience with fun, creative, and engaging content"),
    BUILD_PERSONAL_BRAND("Build and strengthen personal brand as a reference in the field of expertise"),
    PROMOTE_SERVICES_AND_PRODUCTS("Promote and advertise services, products, or solutions offered"),
    STRENGTHEN_AUTHORITY("Strengthen authority and credibility in the market as an expert");

    private final String description;

}
