package com.alex.che.stateoflowa.controller;

import com.alex.che.stateoflowa.dto.VoterDTO;
import com.alex.che.stateoflowa.mapper.MonthlyVoterRegistrationMapper;
import com.alex.che.stateoflowa.model.MonthlyVoterRegistration;
import com.alex.che.stateoflowa.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class VotersController {
    private VoterService voterService;

    @Autowired
    public VotersController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping("/get_voters")
    public ResponseEntity<MonthlyVoterRegistration> getMonthlyVoterRegistrations(@RequestParam(name = "county", required = false) String county,
                                                                @RequestParam(name = "month", required = false) Integer month,
                                                                @RequestParam(name = "party", required = false) String party,
                                                                @RequestParam(name = "active_status", required = false) String active_status,
                                                                @RequestParam(name = "limit", required = false) Integer limit) {
        // TODO what to do with active_status ???
        List<VoterDTO> voterDTOS = voterService.getVotersByParams(county, month, limit);
        return ResponseEntity.ok(MonthlyVoterRegistrationMapper.map(voterDTOS, limit, party));
    }
}
