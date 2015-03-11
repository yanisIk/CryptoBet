package Entities;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Btc_wallet")
public class BtcWalletEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	@OneToOne(mappedBy="Btc_wallet")
	private UserEntity user;
	private String adress;
	private double amount;
	@OneToMany(mappedBy="Btc_wallet")
	private List<TransactionEntity> transactions;

        
    public long getId(){
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
       
    
    
    public boolean withDraw(double btcBetValueTeam1) {
        if(btcBetValueTeam1 > amount){
            return false;
        }
        else{
            amount = amount - btcBetValueTeam1;
            return true;
        }
    }
}