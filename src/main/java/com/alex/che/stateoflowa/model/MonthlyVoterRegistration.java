package com.alex.che.stateoflowa.model;

import com.alex.che.stateoflowa.dto.VoterDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MonthlyVoterRegistration {
    private List<VoterDTO> data;
}
