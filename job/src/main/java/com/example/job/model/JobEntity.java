package com.example.job.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class JobEntity {

    private String id;

    private String type;

    private String url;

    @JsonProperty("created_at")
    private String createdAt;

    private String company;

    @JsonProperty("company_url")
    private String companyUrl;

    private String location;

    private String title;

    private String description;

    @JsonProperty("how_to_apply")
    private String howToApply;

    @JsonProperty("company_logo")
    private String companyLogo;

}