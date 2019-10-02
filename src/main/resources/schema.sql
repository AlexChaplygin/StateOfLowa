DROP TABLE IF EXISTS VOTER;

CREATE TABLE VOTER
(
    id                         VARCHAR(100),
    date                       date,
    month                      integer,
    fips                       VARCHAR(100),
    county                    VARCHAR(100),
    democrat_active            VARCHAR(100),
    democrat_inactive          VARCHAR(100),
    republican_active          VARCHAR(100),
    libertarian_active         VARCHAR(100),
    no_party_active            VARCHAR(100),
    other_active               VARCHAR(100),
    total_active               VARCHAR(100),
    republican_inactive        VARCHAR(100),
    libertarian_inactive       VARCHAR(100),
    no_party_inactive          VARCHAR(100),
    other_inactive             VARCHAR(100),
    total_inactive             VARCHAR(100),
    grand_total                VARCHAR(100),
    primary_lat_dec            VARCHAR(100),
    primary_long_dec           VARCHAR(100),
    primary_county_coordinates VARCHAR(100)
);