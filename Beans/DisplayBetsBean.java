@Stateless
@ManagedBean
public class DisplayBetsBean{

	private List<BetEntity> nonStartedBets_;
	private List<BetEntity> inProgressBets_;
	private List<BetEntity> finishedBets_;
	
	@Inject
	private BetDao betDao_;
	@Inject
	private TransactionDao transactionDao_;
	
	///  Getters and Setters ///
	public List<BetEntity> getNonStartedBets(){
		if(nonStartedBets_ == null){
			nonStartedBets_ = betDao.getNonStartedBets();
		}
		return nonStartedBets_;
	}

	public List<BetEntity> getInProgressBets(){
		if(inProgressBets_ == null){
			inProgressBets_ = betDao.getInProgressBets();
		}
		return inProgressBets_;
	}
	
	public List<BetEntity> getFinishedBets(){
		if(finishedBets_ == null){
			finishedBets_ = betDao.getfinishedBets();
		}
		return finishedBets_;
	}
	
	///       ///
	public BetEntity refreshBet(BetEntity bet){
		return BetUtil.refreshBet(bet);
	}
	

}