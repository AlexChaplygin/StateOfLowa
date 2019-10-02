package com.alex.che.stateoflowa.configuration;

import com.alex.che.stateoflowa.entity.Voter;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class RecordFieldSetMapper implements FieldSetMapper<Voter> {

    public Voter mapFieldSet(FieldSet fieldSet) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Voter voter = new Voter();

        String dateString = fieldSet.readString("Date");
        try {
            Date date = dateFormat.parse(dateString);
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            voter.setDate(dateFormat.parse(dateString));
            voter.setMonth(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        voter.setFips(fieldSet.readString("FIPS"));
        voter.setCounty(fieldSet.readString("County"));
        voter.setDemocratActive(fieldSet.readString("Democrat - Active"));
        voter.setRepublicanActive(fieldSet.readString("Republican - Active"));
        voter.setLibertarianActive(fieldSet.readString("Libertarian - Active"));
        voter.setNoPartyActive(fieldSet.readString("No Party - Active"));
        voter.setOtherActive(fieldSet.readString("Other - Active"));
        voter.setTotalActive(fieldSet.readString("Total - Active"));
        voter.setDemocratInactive(fieldSet.readString("Democrat - Inactive"));
        voter.setRepublicanInactive(fieldSet.readString("Republican - Inactive"));
        voter.setLibertarianInactive(fieldSet.readString("Libertarian - Inactive"));
        voter.setNoPartyInactive(fieldSet.readString("No Party - Inactive"));
        voter.setOtherInactive(fieldSet.readString("Other - Inactive"));
        voter.setTotalInactive(fieldSet.readString("Total - Inactive"));
        voter.setGrandTotal(fieldSet.readString("Grand Total"));
        voter.setPrimaryLatDec(fieldSet.readString("Primary Lat Dec"));
        voter.setPrimaryLongDec(fieldSet.readString("Primary Long Dec"));
        voter.setPrimaryCountyCoordinates(fieldSet.readString("Primary County Coordinates"));
        return voter;
    }
}
