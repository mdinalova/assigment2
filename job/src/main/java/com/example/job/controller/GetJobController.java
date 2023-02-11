package com.example.job.controller;

import com.example.job.model.JobEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RestController
@Slf4j
public class GetJobController {

    @Value("${job-list.url}")
    private String getJobListUrl;

    @Value("${job-detail.url}")
    private String getJobDetailUrl;


    @GetMapping(value = "/api/v1/job/list")
    public List<JobEntity> getJobList() throws JsonProcessingException {

        List<JobEntity> jobEntityArrayList = new ArrayList<>();

        JobEntity jobEntity = new JobEntity();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {

            String response = restTemplate.exchange(getJobListUrl, HttpMethod.GET, request, String.class).getBody();

            JSONArray dataJsonArray = new JSONArray(response);

            for(int i=0; i < dataJsonArray.length(); i++) {
                JSONObject dataJsonObject = dataJsonArray.getJSONObject(0);

                jobEntity.setId(dataJsonObject.getString("id"));
                jobEntity.setType(dataJsonObject.getString("type"));
                jobEntity.setUrl(dataJsonObject.getString("url"));
                jobEntity.setCreatedAt(dataJsonObject.getString("created_at"));
                jobEntity.setCompany(dataJsonObject.getString("company"));
                jobEntity.setCompanyUrl(dataJsonObject.getString("company_url"));
                jobEntity.setLocation(dataJsonObject.getString("location"));
                jobEntity.setTitle(dataJsonObject.getString("title"));
                jobEntity.setDescription(dataJsonObject.getString("description"));
                jobEntity.setHowToApply(dataJsonObject.getString("how_to_apply"));
                jobEntity.setCompanyLogo(dataJsonObject.getString("company_logo"));

                jobEntityArrayList.add(jobEntity);
            }
        } catch (Exception e) {
            log.info("ERROR CALL API GET JOB LIST : " + e.getMessage());
            return Collections.emptyList();
        }

        return jobEntityArrayList;
}


    @GetMapping(value = "/api/v1/job/detail")
    public JobEntity getJobDetail(@RequestParam String ID) throws JsonProcessingException {

        JobEntity jobEntity = new JobEntity();

        JobEntity geJobEntity = null;

        RestTemplate restTemplate = new RestTemplate();

        String completeURL = getJobDetailUrl+ID;

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {

            String response = restTemplate.exchange(completeURL, HttpMethod.GET, request, String.class).getBody();

            JSONObject dataJsonObject = new JSONObject(response);

                jobEntity.setId(dataJsonObject.getString("id"));
                jobEntity.setType(dataJsonObject.getString("type"));
                jobEntity.setUrl(dataJsonObject.getString("url"));
                jobEntity.setCreatedAt(dataJsonObject.getString("created_at"));
                jobEntity.setCompany(dataJsonObject.getString("company"));
                jobEntity.setCompanyUrl(dataJsonObject.getString("company_url"));
                jobEntity.setLocation(dataJsonObject.getString("location"));
                jobEntity.setTitle(dataJsonObject.getString("title"));
                jobEntity.setDescription(dataJsonObject.getString("description"));
                jobEntity.setHowToApply(dataJsonObject.getString("how_to_apply"));
                jobEntity.setCompanyLogo(dataJsonObject.getString("company_logo"));

        } catch (Exception e) {
            log.info("ERROR CALL API GET JOB DETAIL : " + e.getMessage());
            return null;
        }

        return jobEntity;
    }

}