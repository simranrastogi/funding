package com.foriegnexchange.funding.controller;

import com.foriegnexchange.funding.model.FundingModel;
import com.foriegnexchange.funding.service.FundingService;
import com.foriegnexchange.funding.service.NotificationProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/fundings")
public class FundingController {

    @Autowired
    FundingService fundingService;


    private static final Logger logger = LogManager.getLogger(FundingController.class);

    @PostMapping
    public FundingModel saveFund(@RequestBody FundingModel fund) throws InterruptedException {
        Thread t = new Thread();
        t.sleep(10000);
//        try {
//            Thread.sleep(5);}
//        catch (InterruptedException e){
//            System.out.println(e.getMessage());
//        }
        return fundingService.saveFunding(fund);
    }

    @PostMapping("/updateFund")
    public FundingModel updateFund(@RequestBody FundingModel fund) {
        logger.info(" inside fund controller update method :: "+fund);
        return fundingService.updateFunding(fund);
    }
    @GetMapping("/{id}")
    public FundingModel getFundById(@PathVariable Long id) {
        logger.info("inside funding controller getfundbyid method txn id {}" , id);
        Optional<FundingModel> fundingModel=fundingService.getFunding(id);
        FundingModel fundObj = null;
        if(fundingModel.isPresent()){
            logger.info("inside funding controller getfundbyid method funding data {}" , fundingModel);
            fundObj = fundingModel.get();
        }
        return fundObj;
    }

    @DeleteMapping("/{id}")
    public void deleteFund(@PathVariable Long id) {
        fundingService.deleteFunding(id);
    }

    @GetMapping("/getAllFund")
    public List<FundingModel> getAllFunds() {
        return fundingService.getAllFunds();
    }


    @GetMapping("/getAllFundInProgress")
    public List<FundingModel> getAllFundsInProgressTxn() {
        return fundingService.getAllFundsInProgressTxn();
    }

    @PostMapping("/saveAllFunds")
    public void saveAllFunds(@RequestBody List<FundingModel> list){
        fundingService.saveAllFunds(list);
    }

}
