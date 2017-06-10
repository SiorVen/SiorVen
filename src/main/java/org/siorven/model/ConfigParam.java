package org.siorven.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Holds a parameter and its value as a key value
 */
@Entity
public class ConfigParam {

    public static final String SUGG_MAXMIN_RATIOMIN = "SuggestionMAXMIN_MinRatio";
    public static final String SUGG_MAXMIN_RATIOMAX = "SuggestionMAXMIN_MaxRatio";
    public static final String SUGG_MAXMIN_DAYPERIOD = "SuggestionMAXMIN_SuccesSales";

    public static final String SUGG_APRIORI_SUCCESSALES = "SuggestionAPRIORI_SuccesSales";
    public static final String SUGG_APRIORI_DAYPERIOD = "SuggestionAPRIORI_DayPeriod";

    @Id
    private String key;

    private String value;

    public ConfigParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ConfigParam() {
        //empty constructor
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

