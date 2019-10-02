package com.alex.che.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VoterDTO {
    private Long id;
    private Date date;
    private String fips;
    private String country;
    private String democratActive;
    private String democratInactive;
    private String republicanActive;
    private String libertarianActive;
    private String noPartyActive;
    private String otherActive;
    private String totalActive;
    private String republicanInactive;
    private String libertarianInactive;
    private String noPartyInactive;
    private String otherInactive;
    private String totalInactive;
    private String grandTotal;
    private String primaryLatDec;
    private String primaryLongDec;
    private String primaryCountyCoordinates;
}
