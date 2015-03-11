package Util;

import Entities.BetEntity;
import Entities.TransactionEntity;
import java.util.List;


public class BetUtil{

	public static boolean checkIsStartedBet(BetEntity bet){
		/*if( bet.getStartDate()   ){ //Check if start date is passed || is now
			bet.setIsStarted(true);
			return true;
		}*/
		return false;
	}
	
	public static boolean checkIsFinishedBet(BetEntity bet){
	/*	
            if(bet.getFinishDate()   ){ //Check if finish date is passed || is now
			bet.setIsFinished(true);
			return true;
		}
                */
		return false;
	}
	
	public static BetEntity initiateBet(BetEntity bet){
		bet.setIsStarted(true);
		return bet;
	}
	
	public static BetEntity closeBet(BetEntity bet){
		bet.setIsFinished(true);
		//.....
                return bet;
	}

    public static void manageBet(BetEntity bet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    public static BetEntity distributeRewards(BetEntity bet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}