package biz;

import entity.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class QueueInfoEJB {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Reservation> getReservationsByScenicId(int scenicId) {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r WHERE r.scenic.id = :scenicId", Reservation.class);
        query.setParameter("scenicId", scenicId);
        return query.getResultList();
    }
}
