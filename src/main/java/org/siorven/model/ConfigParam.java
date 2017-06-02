package org.siorven.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ander on 01/06/2017.
 */
@Entity
public class ConfigParam {

    public final static String SUGG_MAXMIN_RATIOMIN = "SuggestionMAXMIN_MinRatio";
    public final static String SUGG_MAXMIN_RATIOMAX = "SuggestionMAXMIN_MaxRatio";
    public final static String SUGG_MAXMIN_DAYPERIOD = "SuggestionMAXMIN_SuccesSales";

    public final static String SUGG_APRIORI_SUCCESSALES = "SuggestionAPRIORI_SuccesSales";
    public final static String SUGG_APRIORI_DAYPERIOD = "SuggestionAPRIORI_DayPeriod";

    @Id
    String key;

    String value;

    public ConfigParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ConfigParam() {
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

