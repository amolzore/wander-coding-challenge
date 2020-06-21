package com.wander.coding.challenge.coiddashboard.service;

import java.util.List;
import java.util.Map;
/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
public interface CovidDashboardService {
    List getCountries();
    List getAllCaseFromDayOneByCountryAndStatus(String country, String status);
    List getAllCaseFromDayOneByCountry(String country);
    List getAllCaseFromDayOneLiveByCountryAndStatus(String country, String status);
    List getAllCaseFromDayOneTotalByCountryAndStatus(String country, String status);
    List getAllCaseFromDayOneTotalByCountry(String country);

    Map getAllCaseFromDayOneTotalGraphDataByCountry(String country);
}
