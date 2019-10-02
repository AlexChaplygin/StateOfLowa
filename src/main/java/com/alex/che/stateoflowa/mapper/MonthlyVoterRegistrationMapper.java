package com.alex.che.stateoflowa.mapper;

import com.alex.che.stateoflowa.dto.VoterDTO;
import com.alex.che.stateoflowa.model.MonthlyVoterRegistration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyVoterRegistrationMapper {

    public static MonthlyVoterRegistration map(List<VoterDTO> voterDTOS, Integer limit, final String party) {
        MonthlyVoterRegistration monthlyVoterRegistration = new MonthlyVoterRegistration();
        voterDTOS.forEach(v -> {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("County", v.getCounty());
            dataMap.put("Date", v.getDate().toString());
            dataMap.put("Grand Total", v.getGrandTotal());
            switch (party) {
                case "Democrat":
                    dataMap.put("Democrat - Active", v.getDemocratActive());
                    dataMap.put("Democrat - Inactive", v.getDemocratInactive());
                    break;
                case "Republican":
                    dataMap.put("Republican - Active", v.getRepublicanActive());
                    dataMap.put("Republican - Inactive", v.getRepublicanInactive());
                    break;
                case "Libertarian":
                    dataMap.put("Libertarian - Active", v.getLibertarianActive());
                    dataMap.put("Libertarian - Inactive", v.getLibertarianInactive());
                    break;
                case "No Party":
                    dataMap.put("No Party - Active", v.getNoPartyActive());
                    dataMap.put("No Party - Inactive", v.getNoPartyInactive());
                    break;
                case "Other":
                    dataMap.put("Other - Active", v.getOtherActive());
                    dataMap.put("Other - Inactive", v.getOtherInactive());
                    break;
                default:
                    dataMap.put("Democrat - Active", v.getDemocratActive());
                    dataMap.put("Democrat - Inactive", v.getDemocratInactive());
                    dataMap.put("Republican - Active", v.getRepublicanActive());
                    dataMap.put("Republican - Inactive", v.getRepublicanInactive());
                    dataMap.put("Libertarian - Active", v.getLibertarianActive());
                    dataMap.put("Libertarian - Inactive", v.getLibertarianInactive());
                    dataMap.put("No Party - Active", v.getNoPartyActive());
                    dataMap.put("No Party - Inactive", v.getNoPartyInactive());
                    dataMap.put("Other - Active", v.getOtherActive());
                    dataMap.put("Other - Inactive", v.getOtherInactive());
            }
            monthlyVoterRegistration.add(dataMap);
        });

        monthlyVoterRegistration.setNextStart(limit);

        return monthlyVoterRegistration;
    }
}
