package org.siorven.controller.webint;

import org.siorven.controller.webint.forms.AlgorithmConf;
import org.siorven.model.ConfigParam;
import org.siorven.services.ConfigParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andoni on 30/05/2017.
 */
@Controller
public class SuggestionController {

    @Autowired
    private ConfigParamService configParamService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LocaleResolver resolver;


    /**
     * Displays the suggestion manager
     *
     * @return Key for the {@link org.springframework.web.servlet.ViewResolver ViewResolver} bean
     */
    @GetMapping("/suggestion/manager/{id}")
    public String showSuggestionManager(@PathVariable int id, Model model) {
        model.addAttribute("machineId", id);
        return "suggestionManager";
    }

    @GetMapping("/suggestion/config")
    public String showSuggestionConfig(Model model) {
        AlgorithmConf conf = new AlgorithmConf();
        conf.setAsociationUsedDays(configParamService.getInt(ConfigParam.SUGG_APRIORI_DAYPERIOD));
        conf.setMinMaxPeriod(configParamService.getInt(ConfigParam.SUGG_MAXMIN_DAYPERIOD));
        conf.setRatioMax(configParamService.getDouble(ConfigParam.SUGG_MAXMIN_RATIOMAX));
        conf.setRatioMin(configParamService.getDouble(ConfigParam.SUGG_MAXMIN_RATIOMIN));
        conf.setSuccessSaleNo(configParamService.getInt(ConfigParam.SUGG_APRIORI_SUCCESSALES));
        model.addAttribute("conf", conf);
        return "suggestionConfig";
    }

    @PostMapping("/suggestion/config")
    public String editConf(@ModelAttribute("conf") @Validated AlgorithmConf algorithmConf, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors())
            return "suggestionConfig";

        configParamService.save(ConfigParam.SUGG_APRIORI_DAYPERIOD, algorithmConf.getAsociationUsedDays());
        configParamService.save(ConfigParam.SUGG_APRIORI_SUCCESSALES, algorithmConf.getSuccessSaleNo());
        configParamService.save(ConfigParam.SUGG_MAXMIN_RATIOMIN, algorithmConf.getRatioMin());
        configParamService.save(ConfigParam.SUGG_MAXMIN_RATIOMAX, algorithmConf.getRatioMax());
        configParamService.save(ConfigParam.SUGG_MAXMIN_DAYPERIOD, algorithmConf.getMinMaxPeriod());

        String msg = messageSource.getMessage("message.config.edited", null, resolver.resolveLocale(request));
        redirectAttributes.addFlashAttribute("message", msg);
        return "redirect:/";
    }


}
