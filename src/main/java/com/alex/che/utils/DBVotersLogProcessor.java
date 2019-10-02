package com.alex.che.utils;

import com.alex.che.entity.Voter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class DBVotersLogProcessor implements ItemProcessor<Voter, Voter> {

    public Voter process(Voter voter) {
        System.out.println("Inserting employee : " + voter);
        return voter;
    }
}
