package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "scenic_id")
    private Scenic scenic;

    @Column(name = "visit_time")
    private Timestamp visitTime;
    
    private String status;
    
    @Column(name = "ticket_code")
    private String ticketCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public User getUser(){
    	return this.user;
    }
    
    public void setUser(User user){
    	this.user=user;
    }
    
    public Scenic getScenic(){
    	return this.scenic;
    }
    
    public void setScenic(Scenic scenic){
    	this.scenic=scenic;
    }
    
    public Timestamp getVisitTime(){
    	return this.visitTime;
    }
    
    public void setVisitTime(Timestamp visitTime){
    	this.visitTime=visitTime;
    }
    
    public String getStatus(){
    	return this.status;
    }
    
    public void setStatus(String status){
    	this.status=status;
    }
    
    public String getTicketCode(){
    	return this.ticketCode;
    }
    
    public void setTicketCode(String ticketCode){
    	this.ticketCode=ticketCode;
    }
}
