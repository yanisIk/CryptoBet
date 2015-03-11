package Entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Where;

@Entity
@Table(name="Bet")

 @NamedQuery(name = "BetEntity.findAll", query = "SELECT b FROM Bet b")
 @NamedQuery(name = "BetEntity.findNonStarted", query = "SELECT b FROM Bet b WHERE b.is_started=0, b.is_approved=1")
 @NamedQuery(name = "BetEntity.findInProgress", query = "SELECT b FROM Bet b WHERE b.is_started=1, b.is_finished=0")
 @NamedQuery(name = "BetEntity.findNonApproved", query = "SELECT b FROM Bet b WHERE b.is_approved=0")
 @NamedQuery(name = "BetEntity.findDota2", query = "SELECT b FROM Bet b WHERE b.game_name ='"+GameEntity.Dota2+"'")
 @NamedQuery(name = "BetEntity.findLol", query = "SELECT b FROM Bet b WHERE b.game_name ='"+GameEntity.Lol+"'")
 @NamedQuery(name = "BetEntity.findCsgo", query = "SELECT b FROM Bet b WHERE b.game_name ='"+GameEntity.Csgo+"'")
    

public class BetEntity{
	
    public static final String FIND_BY_ID = "";
    public static final String FIND_NON_STARTED = "BetEntity.findNonStarted";
    public static final String FIND_IN_PROGRESS = "BetEntity.findInProgress";
    public static final String FIND_NON_APPROVED = "BetEntity.findNonApproved";
    public static final String FIND_DOTA2 = "BetEntity.findDota2";
    public static final String FIND_LOL = "BetEntity.findLol";
    public static final String FIND_CSGO = "BetEntity.findCsgo";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="game_name")
    private String videoGameName;
    @Column(name="team_1_name")
    private String team1Name;
    @Column(name="team_2_name")
    private String team2Name;
    @OneToMany(mappedBy="Bet")
    @Where(clause = "team_name = '"+getTeam1Name()+"'") //Requires Hibernate
    @OrderBy("amount")
    private List<TransactionEntity> team1TransactionList;
    @OneToMany(mappedBy="Bet")
    @Where(clause = "team_name = '"+getTeam2Name()+"'") //Requires Hibernate
    @OrderBy("amount")
    private List<TransactionEntity> team2TransactionList;
    @Column(name="is_approved")
    private boolean isApproved = false;
    @Column(name="is_started")
    private boolean isStarted = false;
    @Column(name="is_finished")
    private boolean isFinished = false;
    @Column(name="team_winner")
    private String winnerTeam;
	
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="start_date")
    private java.util.Calendar startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="finish_date")
    private java.util.Calendar finishDate;
	
    @Transient
    private double team1TotalBTC;
    @Transient
    private double team2TotalBTC;

    public long getId(){
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoGameName() {
        return videoGameName;
    }

    public void setVideoGameName(String videoGameName) {
        this.videoGameName = videoGameName;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public List<TransactionEntity> getTeam2TransactionList() {
        return team2TransactionList;
    }

    public void setTeam2TransactionList(List<TransactionEntity> team2TransactionList) {
        this.team2TransactionList = team2TransactionList;
    }
    
    public List<TransactionEntity> getTeam1TransactionList() {
        return team1TransactionList;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isIsStarted() {
        return isStarted;
    }

    public void setIsStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Calendar finishDate) {
        this.finishDate = finishDate;
    }

    public double getTeam1TotalBTC() {
        return team1TotalBTC;
    }

    public void setTeam1TotalBTC(double team1TotalBTC) {
        this.team1TotalBTC = team1TotalBTC;
    }

    public double getTeam2TotalBTC() {
        return team2TotalBTC;
    }

    public void setTeam2TotalBTC(double team2TotalBTC) {
        this.team2TotalBTC = team2TotalBTC;
    }
	
	
    public void addTransactionTeam1(TransactionEntity tr){
            team1TransactionList.add(tr);
    }
    public void addTransactionTeam2(TransactionEntity tr){
            team2TransactionList.add(tr);
    }
	
}