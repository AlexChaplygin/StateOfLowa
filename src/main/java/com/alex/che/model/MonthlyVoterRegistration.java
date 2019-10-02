package com.alex.che.model;

import com.alex.che.dto.VoterDTO;
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
