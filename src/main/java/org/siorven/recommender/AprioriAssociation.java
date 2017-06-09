package org.siorven.recommender;

import org.siorven.controller.handlers.errors.ResourceNotFoundException;
import org.siorven.model.*;
import org.siorven.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weka.associations.*;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import javax.servlet.ServletContext;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Class that prepares an runs the apriori algorithm implemented in Weka library
 */
@Component
public class AprioriAssociation {

    public static final double APRIORI_MIN_METRIC = 0.5;
    public static final int APRIORI_NUM_RULES = 8;

    @Autowired
    private SuggestionService suggestionService;

    private Timestamp finishDate;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private IAService iaService;

    /**
     * Function that runs Apriori
     * @param outData
     */
    public void runApriori(Instances outData) { //Instances outData, Machine machine
        ArffLoader loader = new ArffLoader();
        try {
            loader.setSource(new File(servletContext.getRealPath("/WEB-INF/data/dataset1.arff")));

            // build associator and configure parameters
            Apriori apriori = prepareAprioriAssociator(outData);

            //Separate the result into rules
            finishDate = new Timestamp(new Date().getTime());
            suggestionService.saveSuggestionList(iaService.getSuggestionsFromAprioriRules(apriori,finishDate));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that configures Apriori algorithm parameters
     *
     * @param data instances generated from arff file
     * @return
     * @throws Exception
     */
    private Apriori prepareAprioriAssociator(Instances data) throws Exception {
        Apriori apriori = new Apriori();
        apriori.setMinMetric(APRIORI_MIN_METRIC);
        apriori.setNumRules(APRIORI_NUM_RULES);
        apriori.setClassIndex(data.classIndex());
        apriori.buildAssociations(data);
        return apriori;
    }

}
