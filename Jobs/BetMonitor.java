package Util;


import Dao.BetDao;
import Entities.BetEntity;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import javax.inject.Inject;

@Singleton
@Startup
public class BetMonitor{

    @Inject
    BetDao betDao;

    @PostConstruct
    private void init(){
        
    }

    @Schedule(minute="*/5", hour="*", persistent = false)
    private void monitorNonStartedBets() {
            List<BetEntity> nonStartedBets = betDao.getNonStartedBets(); 
            for(BetEntity bet : nonStartedBets){
               if(BetUtil.checkIsStartedBet(bet)){
                    bet = BetUtil.initiateBet(bet);
                    betDao.update(bet);
                } 
            }  
    }

    @Schedule(minute="*/5", hour="*", persistent = false)
    private void monitorStartedBets(){
        List<BetEntity> inProgressBets = betDao.getInProgressBets(); 
        for(BetEntity bet : inProgressBets){
            if(BetUtil.checkIsStartedBet(bet)){
            bet = BetUtil.closeBet(bet);
            betDao.update(bet);
        } 
        }
    }
    
    @Schedule(minute="*/5", hour="*", persistent = false)
    private void monitorFinishedBets(){
        
        List<BetEntity> finishedBets = betDao.getFinishedBets(); 
        for(BetEntity bet : finishedBets){
            bet = BetUtil.distributeRewards(bet);
            betDao.update(bet);
        } 
        
    }
}

