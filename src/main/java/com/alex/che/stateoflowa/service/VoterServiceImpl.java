package com.alex.che.stateoflowa.service;

import com.alex.che.stateoflowa.dto.VoterDTO;
import com.alex.che.stateoflowa.entity.Voter;
import com.alex.che.stateoflowa.repository.VoterRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoterServiceImpl implements VoterService {

    private VoterRepository voterRepository;
    private ModelMapper mapper;

    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository,
                            ModelMapper mapper) {
        this.voterRepository = voterRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<VoterDTO> getVotersByParams(String county,
                                            Integer month,
                                            String party,
                                            String active_status,
                                            Integer limit) {
        List<Voter> voters = voterRepository.getVotersByParams();
        return mapper.map(voters, new TypeToken<List<VoterDTO>>() {
        }.getType());
    }
}
