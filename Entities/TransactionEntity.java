package Entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity @EntityListeners(TransactionListener.class)
@Table(name="Transaction")
@NamedQueries({
 @NamedQuery(name = "TransactionEntity.findAll", query = "SELECT t FROM Transaction t")
 @NamedQuery(name = "TransactionEntity.findAllBTC", query = "SELECT t FROM Transaction t WHERE t.money = '"+Money.BTC+"'")
 @NamedQuery(name = "TransactionEntity.findInbound", query = "SELECT t FROM Transaction t WHERE t.is_inbound=1, t.bet_ID= :betID, t.team_name= :teamName")
 @NamedQuery(name = "TransactionEntity.findRewards", query = "SELECT t FROM Transaction t WHERE t.is_reward=1, t.bet_ID= :betID")
 @NamedQuery(name = "TransactionEntity.findWallet", query = "SELECT t FROM Transaction t WHERE t.wallet_ID= :walletID")
})
public class TransactionEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	@ManyToOne
	@JoinColumn(name="bet_ID", referencedColumnName="ID")
	private BetEntity bet;
	@ManyToOne
	@JoinColumn(name="wallet_ID", referencedColumnName="ID")
	private BtcWalletEntity btcWallet;
	@Column(name="team_name")
	private String teamName;
	@Column(name="amount")
	private double amount;
	@Column(name="money")
	private String money;
	@Column(name="reception_adress")
	private String receptionAdress;
	@Temporal(javax.persistence.TemporalType.DATE)
        @Column(name="date")
	private java.util.Calendar date;
	@Column(name="is_inbound")
	private boolean isInbound;
	@Column(name="is_reward")
	private boolean isReward;

        
        public long getId(){
        return id;
    }
        
    public BetEntity getBet() {
       return bet;
    }

    public void setBet(BetEntity bet) {
        this.bet = bet;
    }

    public BtcWalletEntity getBtcWallet() {
        return btcWallet;
    }

    public void setBtcWallet(BtcWalletEntity btcWallet) {
        this.btcWallet = btcWallet;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getReceptionAdress() {
        return receptionAdress;
    }

    public void setReceptionAdress(String receptionAdress) {
        this.receptionAdress = receptionAdress;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean isIsInbound() {
        return isInbound;
    }

    public void setIsInbound(boolean isInbound) {
        this.isInbound = isInbound;
    }

    public boolean isIsReward() {
        return isReward;
    }

    public void setIsReward(boolean isReward) {
        this.isReward = isReward;
    }
}