package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    private String username;
    private String password;
    private String email;
    
    @ManyToMany
    @JoinTable(
        name = "user_scenic",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "scenic_id")
    )
    private Set<Scenic> visScenics = new HashSet<Scenic>();
    
    @OneToMany(mappedBy = "user")
    private Set<Reservation> reservations = new HashSet<Reservation>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setEmail(String email){
    	this.email=email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getEmail(){
    	return this.email;
    }
    
    public Set<Scenic> getVisScenics() {
        return visScenics;
    }

    public void setVisScenics(Set<Scenic> visScenics) {
        this.visScenics = visScenics;
    }
    
    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
