package org.siorven.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ander on 24/05/2017.
 */
@Component
public class SuggestionProcessor {


    @Scheduled(cron = "0 0 * * *") //Every midnight
    public void processSuggestions() {
        //TODO perform suggestion processing
    }

}
