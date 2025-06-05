package biz;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Reservation;
import entity.User;
import entity.Scenic;
import java.sql.Timestamp;

@Stateful
public class ReservationBean {
    
	@PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public boolean makeReservation(int userId, int scenicId, Timestamp visitTime) {
        try {
            User user = em.find(User.class, userId);
            Scenic scenic = em.find(Scenic.class, scenicId);

            if (user == null || scenic == null) {
                return false;
            }

            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setScenic(scenic);
            reservation.setVisitTime(visitTime);
            reservation.setStatus("PENDING");
            reservation.setTicketCode(generateTicketCode());

            em.persist(reservation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private String generateTicketCode() {
        return "TICKET" + System.currentTimeMillis();
    }
}

