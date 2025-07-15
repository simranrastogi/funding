package com.foriegnexchange.funding.repository;

import com.foriegnexchange.funding.model.FundingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingRepository extends JpaRepository<FundingModel, Long > {
    List<FundingModel> findByTxnStatus(String txnStatus);

    //@Query("SELECT f FROM FundingModel f WHERE f.txnStatus = :status")
    //List<FundingModel> findAllByTxnStatus(@Param("status") String status);


}
