package org.siorven.controller.webint.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Class that defines the model for the form of the configuration of
 * the suggestion processing algorithm
 */
public class AlgorithmConf {

    @NotNull(message = "{confAlg.CantBeEmpty}")
    @Min(value = 0, message = "{confAlg.error.minVal0}")
    @Max(value = 1, message = "{confAlg.error.maxVal1}")
    private Double ratioMin;

    @NotNull(message = "{confAlg.CantBeEmpty}")
    @Min(value = 1, message = "{confAlg.error.minVal1}")
    private Double ratioMax;

    @NotNull(message = "{confAlg.CantBeEmpty}")
    @Min(value = 0, message = "{confAlg.error.minVal0}")
    private Integer successSaleNo;

    @NotNull(message = "{confAlg.CantBeEmpty}")
    @Min(value = 0, message = "{confAlg.error.minVal0}")
    private Integer asociationUsedDays;

    @NotNull(message = "{confAlg.CantBeEmpty}")
    @Min(value = 0, message = "{confAlg.error.minVal0}")
    private Integer minMaxPeriod;

    public AlgorithmConf() {
        ratioMin = 0.5;
        ratioMax = 0.5;
        successSaleNo = 10;
        asociationUsedDays = 20;
        minMaxPeriod = 10;
    }

    public Double getRatioMin() {
        return ratioMin;
    }

    public void setRatioMin(Double ratioMin) {
        this.ratioMin = ratioMin;
    }

    public Double getRatioMax() {
        return ratioMax;
    }

    public void setRatioMax(Double ratioMax) {
        this.ratioMax = ratioMax;
    }

    public Integer getSuccessSaleNo() {
        return successSaleNo;
    }

    public void setSuccessSaleNo(Integer successSaleNo) {
        this.successSaleNo = successSaleNo;
    }

    public Integer getAsociationUsedDays() {
        return asociationUsedDays;
    }

    public void setAsociationUsedDays(Integer asociationUsedDays) {
        this.asociationUsedDays = asociationUsedDays;
    }

    public Integer getMinMaxPeriod() {
        return minMaxPeriod;
    }

    public void setMinMaxPeriod(Integer minMaxPeriod) {
        this.minMaxPeriod = minMaxPeriod;
    }
}
