package com.foriegnexchange.funding.service;

import com.foriegnexchange.funding.model.FundingModel;
import com.foriegnexchange.funding.repository.FundingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FundingService {
    @Autowired
    FundingRepository fundingRepository;
    private static final Logger logger = LogManager.getLogger(FundingService.class);

    @Autowired
    NotificationProducer notificationProducer;


    public void send(String message ){
        logger.info("inside funding controller send notification kafka");
        notificationProducer.sendNotification(message);
    }

    public FundingModel saveFunding(FundingModel fundingModel) {
        logger.info("inside funding service save funding method: "+ fundingModel);
       FundingModel fundObj =  fundingRepository.save(fundingModel);
        logger.info("fund object {}",fundObj);
       send("Funding Record Inserted sucessfully :: "+fundObj);
        return fundObj;
    }

    public FundingModel updateFunding(FundingModel fundingModel) {
        logger.info("inside funding service update funding method: "+ fundingModel);
    Optional<FundingModel> fund =  fundingRepository.findById(fundingModel.getId());
            FundingModel fundObj = fund.get();
       String setStatusIntoFund = fundingModel.getTxnStatus();
        fundObj.setTxnStatus(setStatusIntoFund);
        logger.info("inside funding service update fund object:  {} ",fundObj);
        return fundingRepository.save(fundObj);
    }

    public void deleteFunding(Long id){
        fundingRepository.deleteById(id);
    }

    public Optional<FundingModel> getFunding(Long id) {
        return fundingRepository.findById(id);
    }


    public List<FundingModel> getAllFunds() {
        return fundingRepository.findAll();
    }
    public List<FundingModel> getAllFundsInProgressTxn() {
       String txnStatus = "INPROGRESS";
        return fundingRepository.findByTxnStatus(txnStatus);
    }
    public void saveAllFunds(List<FundingModel> list){
        List<FundingModel> listofFund = fundingRepository.saveAll(list);
        logger.info("inside saveAllFunds  {} ",listofFund.size());
        listofFund.stream().forEach(x -> send("Transaction status updated :: "+x));

    }

}
