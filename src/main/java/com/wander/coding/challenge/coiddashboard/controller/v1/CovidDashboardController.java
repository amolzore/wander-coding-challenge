package com.wander.coding.challenge.coiddashboard.controller.v1;

import com.wander.coding.challenge.coiddashboard.service.CovidDashboardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 *
 * Covid Dashboard Controller.
 */
@RestController
@RequestMapping("/v1/api/covid-dashboard")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CovidDashboardController {

    @Autowired
    private CovidDashboardServiceImpl covidDashboardService;

    /**
     * Returns all the available countries and provinces, as well as the country slug for per country requests.
     *
     * @return
     */
    @GetMapping("/countries")
    public ResponseEntity getCountries() {
        return new ResponseEntity(covidDashboardService.getCountries(), HttpStatus.OK);
    }

    /**
     * Returns all cases by case type for a country from the first recorded case. \
     * Country must be the Slug from /countries or /summary. Cases must be one of: confirmed, recovered, deaths
     *
     * @param country
     * @param status
     * @return
     */
    @GetMapping("/day-one/{country}/{status}")
    public ResponseEntity getAllCaseFromDayOneByCountryAndStatus(@PathVariable(name = "country", required = true) final String country,
                                                       @PathVariable(name = "status", required = true) final String status) {
        return new ResponseEntity(covidDashboardService.getAllCaseFromDayOneByCountryAndStatus(country, status),HttpStatus.OK);
    }

    /**
     * Returns all cases by case type for a country from the first recorded case. Country must be the Slug from /countries or /summary.
     * Cases must be one of: confirmed, recovered, deaths
     *
     * @param country
     * @return
     */
    @GetMapping("/day-one-all-status/{country}")
    public ResponseEntity getAllCaseFromDayOneByCountry(@PathVariable(name = "country", required = true) final String country) {
        return new ResponseEntity(covidDashboardService.getAllCaseFromDayOneByCountry(country),HttpStatus.OK);
    }

    /**
     * Returns all cases by case type for a country from the first recorded case with the latest record being the live count.
     * Country must be the Slug from /countries or /summary. Cases must be one of: confirmed, recovered, deaths
     *
     * @param country
     * @param status
     * @return
     */
    @GetMapping("/day-one-live/{country}/{status}")
    public ResponseEntity getAllCaseFromDayOneLiveByCountryAndStatus(@PathVariable(name = "country", required = true) final String country,
                                                                     @PathVariable(name = "status", required = true) final String status) {
        return new ResponseEntity(covidDashboardService.getAllCaseFromDayOneLiveByCountryAndStatus(country, status),HttpStatus.OK);
    }

    /**
     * Returns all cases by case type for a country from the first recorded case.
     * Country must be the slug from /countries or /summary. Cases must be one of: confirmed, recovered, deaths
     *
     * @param country
     * @param status
     * @return
     */
    @GetMapping("/day-one-total/{country}/{status}")
    public ResponseEntity getAllCaseFromDayOneTotalByCountryAndStatus(@PathVariable(name = "country", required = true) final String country,
                                                                     @PathVariable(name = "status", required = true) final String status) {
        return new ResponseEntity(covidDashboardService.getAllCaseFromDayOneTotalByCountryAndStatus(country, status),HttpStatus.OK);
    }

    /**
     * Returns all cases by case type for a country from the first recorded case.
     * Country must be the slug from /countries or /summary. Cases must be one of: confirmed, recovered, deaths
     *
     * @param country
     * @return
     */
    @GetMapping("/day-one-total-all-status/{country}")
    public ResponseEntity getAllCaseFromDayOneTotalByCountry(@PathVariable(name = "country", required = true) final String country) {
        return new ResponseEntity(covidDashboardService.getAllCaseFromDayOneTotalByCountry(country),HttpStatus.OK);
    }


    @GetMapping("/graph-data/day-one-total-all-status/{country}")
    public ResponseEntity getAllCaseFromDayOneTotalGraphDataByCountry(@PathVariable(name = "country", required = true) final String country) {
        return new ResponseEntity(covidDashboardService.getAllCaseFromDayOneTotalGraphDataByCountry(country),HttpStatus.OK);
    }
}
