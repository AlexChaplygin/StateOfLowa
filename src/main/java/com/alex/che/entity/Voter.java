package com.alex.che.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "voter")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "fips")
    private String fips;

    @Column(name = "country")
    private String country;

    @Column(name = "democrat_active")
    private String democratActive;

    @Column(name = "democrat_inactive")
    private String democratInactive;

    @Column(name = "republican_active")
    private String republicanActive;

    @Column(name = "libertarian_active")
    private String libertarianActive;

    @Column(name = "no_arty_active")
    private String noPartyActive;

    @Column(name = "other_active")
    private String otherActive;

    @Column(name = "total_active")
    private String totalActive;

    @Column(name = "republican_inactive")
    private String republicanInactive;

    @Column(name = "libertarian_inactive")
    private String libertarianInactive;

    @Column(name = "no_party_inactive")
    private String noPartyInactive;

    @Column(name = "other_inactive")
    private String otherInactive;

    @Column(name = "total_inactive")
    private String totalInactive;

    @Column(name = "grand_total")
    private String grandTotal;

    @Column(name = "primary_lat_dec")
    private String primaryLatDec;

    @Column(name = "primary_long_dec")
    private String primaryLongDec;

    @Column(name = "primary_county_coordinates")
    private String primaryCountyCoordinates;
}
