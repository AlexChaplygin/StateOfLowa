package com.alex.che.service;

import com.alex.che.dto.VoterDTO;

import java.util.List;

public interface VoterService {
    List<VoterDTO> getVotersByParams();
}
