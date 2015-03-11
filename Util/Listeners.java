package Util;


import javax.ejb.Singleton;

@Singleton
public class BetListener{

    @PostPersist void onPostPersist(BetEntity bet) {
	
	}

    @PostUpdate void onPostUpdate(BetEntity bet) {
	
	}
   
    @PostRemove void onPostRemove(BetEntity bet) {
	
	}


}

@Singleton
public class TransactionListener{


    @PostPersist void onPostPersist(TransactionEntity tr) {
	
	}

    @PostUpdate void onPostUpdate(TransactionEntity tr) {
	
	}

    @PostRemove void onPostRemove(TransactionEntity tr) {
	
	}


}