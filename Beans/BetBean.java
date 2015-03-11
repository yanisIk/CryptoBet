package Beans;


import Dao.BetDao;
import Dao.TransactionDao;
import Dao.UserDao;
import Entities.BetEntity;
import Entities.BtcWalletEntity;
import Entities.TransactionEntity;
import Entities.UserEntity;
import Util.BetUtil;
import Util.FaceUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean
@Stateful
public class BetBean{
	
	private BetEntity betEntity;
	private UserEntity userEntity;
	private BtcWalletEntity btcWalletEntity;
	private List<TransactionEntity> allTransactionHistory;
	private List<TransactionEntity> userTransactionHistory;
	private TransactionEntity selectedTransaction;
	private double btcBetValueTeam1;
	private double btcBetValueTeam2;

	@Inject 
	UserDao userDao;
	@Inject
	BetDao betDao;
	@Inject
	TransactionDao transactionDao;

    
    public TransactionEntity getSelectedTransaction() {
        return selectedTransaction;
    }

    public void setSelectedTransaction(TransactionEntity selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

    public double getBtcBetValueTeam1() {
        return btcBetValueTeam1;
    }

    public void setBtcBetValueTeam1(double btcBetValueTeam1) {
        this.btcBetValueTeam1 = btcBetValueTeam1;
    }

    public double getBtcBetValueTeam2() {
        return btcBetValueTeam2;
    }

    public void setBtcBetValueTeam2(double btcBetValueTeam2) {
        this.btcBetValueTeam2 = btcBetValueTeam2;
    }

        
        
        
	public BetEntity getBetEntity(){
		return betEntity;
	}

	public UserEntity getUserEntity(){
		return FaceUtil.getUser();
	}

	public BtcWalletEntity getBtcWalletEntity(){
		if(btcWalletEntity == null){
			btcWalletEntity = userEntity.getBtcWallet();
		}
		return btcWalletEntity;
	}

	public List<TransactionEntity> getAllTransactionHistory(){
		allTransactionHistory = transactionDao.getTransactionsByBet(getBetEntity().getId());
		return allTransactionHistory; 
	}

	public List<TransactionEntity> getUserTransactionHistory(){
		userTransactionHistory = transactionDao.getTransactionsByWalletBet(getBtcWalletEntity().getId(),getBetEntity().getId());
		return userTransactionHistory; 
	}

	public void betBtcForTeam1(){
		//Create transaction
		if(getBtcBetValueTeam1() >= getBtcWalletEntity().getAmount() ){
		
			TransactionEntity tr = new TransactionEntity();
			tr.setAmount(getBtcBetValueTeam1());
			tr.setBet(getBetEntity());
			tr.setBtcWallet(getBtcWalletEntity());
			tr.setIsInbound(true);
			tr.setIsReward(false);
			tr.setDate(new Calendar.Date());
			tr.setMoney("BTC");
			tr.setTeamName(getBetEntity().getTeam1Name());

			betEntity.addTransactionTeam1(tr);
			btcWalletEntity.withDraw(btcBetValueTeam1);

			betDao.save(betEntity);
			userDao.save(userEntity);
		}

		
	}

	public void betBtcForTeam2(){
		//Create transaction
		if(getBtcWalletEntity().getAmount() < getBtcBetValueTeam2() == false){
			
			TransactionEntity tr = new TransactionEntity();
			tr.setAmount(getBtcBetValueTeam2());
                        setBtcBetValueTeam2(0);
			tr.setBet(getBetEntity());
			tr.setBtcWallet(getBtcWalletEntity());
			tr.setIsInbound(true);
			tr.setIsReward(false);
			tr.setDate(new Calendar.Date());
			tr.setMoney("BTC");
			tr.setTeamName(getBetEntity().getTeam2Name());

			betEntity.addTransactionTeam2(tr);
			btcWalletEntity.withDraw(btcBetValueTeam2);

			betDao.save(betEntity);
			userDao.save(userEntity);
		}

	}

	public void removeTransaction(){
		if(BetUtil.checkIsFinishedBet(getBetEntity()) == false){
			userEntity.get
		}
		
	}

}

}