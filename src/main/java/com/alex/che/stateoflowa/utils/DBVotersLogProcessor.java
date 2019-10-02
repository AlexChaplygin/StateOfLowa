package com.alex.che.stateoflowa.utils;

import com.alex.che.stateoflowa.entity.Voter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class DBVotersLogProcessor implements ItemProcessor<Voter, Voter> {

    public Voter process(Voter voter) {
        log.info("Inserting voter from CSV file : " + voter);
        return voter;
    }
}
