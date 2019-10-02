package com.alex.che.stateoflowa.service;

import com.alex.che.stateoflowa.dto.VoterDTO;

import java.util.List;

public interface VoterService {
    List<VoterDTO> getVotersByParams(String county,
                                     Integer month,
                                     Integer limit);
}
