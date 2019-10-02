package com.alex.che.stateoflowa.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MonthlyVoterRegistration {

    @JsonProperty("next_start")
    private Integer nextStart;

    private List<Map<String, String>> data = new ArrayList<>();

    @JsonAnySetter
    public void add(Map<String, String> dataMap) {
        data.add(dataMap);
    }
}
