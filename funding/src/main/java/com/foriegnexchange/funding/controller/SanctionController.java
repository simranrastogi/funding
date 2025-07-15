package com.foriegnexchange.funding.controller;

import com.foriegnexchange.funding.constants.SanctionConstant;
import com.foriegnexchange.funding.model.FundingModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sanction")
public class SanctionController {

    private static final Logger logger = LogManager.getLogger(SanctionController.class);

    @PostMapping
    public FundingModel sanctionStatus(@RequestBody FundingModel fund) {
        logger.info(" inside sanction controller update method :: "+fund);
        if(fund.getAmount() >= 500){
            fund.setSanctionStatus(SanctionConstant.HIT);
        }else {
            logger.info(" inside sanction controller nohit case :: "+fund);
            fund.setSanctionStatus(SanctionConstant.NOHIT);
        }
        return fund;
    }


}
