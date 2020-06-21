package com.wander.coding.challenge.coiddashboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@Service
public class CovidDashboardServiceImpl implements CovidDashboardService  {

    @Value("${covid.api.baseUri}")
    private String covidApiBaseUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List getCountries() {
        String url = covidApiBaseUri.concat("/countries");
        List countries = new ArrayList();
        try {
            countries = restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public List getAllCaseFromDayOneByCountryAndStatus(String country, String status) {

        String url = covidApiBaseUri.concat("/dayone/country/").concat(country).concat("/status/").concat(status);

        List cases = new ArrayList();
        try {
            cases = restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public List getAllCaseFromDayOneByCountry(String country) {

        String url = covidApiBaseUri.concat("/dayone/country/").concat(country);

        List cases = new ArrayList();
        try {
            cases = restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public List getAllCaseFromDayOneLiveByCountryAndStatus(String country, String status) {

        String url = covidApiBaseUri.concat("/dayone/country/").concat(country).concat("/status/").concat(status).concat("/live");

        List cases = new ArrayList();
        try {
            cases = restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }


    @Override
    public List getAllCaseFromDayOneTotalByCountryAndStatus(String country, String status) {

        String url = covidApiBaseUri.concat("/total/dayone/country/").concat(country).concat("/status/").concat(status);

        List cases = new ArrayList();
        try {
            cases = restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public LinkedList getAllCaseFromDayOneTotalByCountry(String country) {

        String url = covidApiBaseUri.concat("/total/dayone/country/").concat(country);

        LinkedList cases = new LinkedList();
        try {
            cases = restTemplate.getForObject(url, LinkedList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public Map getAllCaseFromDayOneTotalGraphDataByCountry(String country) {
        LinkedList data = getAllCaseFromDayOneTotalByCountry(country);
        Map graphData = new HashMap();
        LinkedList categories = new LinkedList();
        LinkedList confirmed = new LinkedList();
        LinkedList deaths = new LinkedList();
        LinkedList recovered = new LinkedList();
        LinkedList active = new LinkedList();
        data.forEach(row->{
            Map rowMap = (Map) row;
            categories.add(((String) rowMap.get("Date")).substring(0,10));
            confirmed.add((Integer) rowMap.get("Confirmed"));
            deaths.add((Integer)rowMap.get("Deaths"));
            recovered.add((Integer)rowMap.get("Recovered"));
            active.add((Integer)rowMap.get("Active"));
        });

        graphData.put("categories",categories);
        graphData.put("confirmed",confirmed);
        graphData.put("deaths",deaths);
        graphData.put("recovered",recovered);
        graphData.put("active",active);

        return graphData;

    }
}
