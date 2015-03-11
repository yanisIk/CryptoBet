package Util;


import java.util.Map;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BTCUtil{

	@Inject
	private BlockChainAPI btcApi;
	
	public static String generateBTCAdress(String myAddress, String callback, boolean anonymous, Map<String, String> params){
		return btcApi.generatePaymentAddress(myAddress, String callback, anonymous,  params);
	}
	
	@Asynchronous
	public static  void sendBTC(String senderAdress, String receptionAdress, double amount){
		if(verifyAdress(senderAdress, receptionAdress)){
			if(amount > 0){
				btcBankApi.send(senderAdress, receptionAdress, double amount);
			}
		}
	}
	
	public static List<TransactionEntity> getBTCTransactions(String adress){
		return btcBankApi.getTransactionList(adress);
	}
	
	public static double convertToBTC(String countryCode, double amount){
		return btcApi.convertToBTC(countryCode, amount);
	}
	
}