package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Scenic;

@Stateless
public class ScenicDetailEJB {
    @PersistenceContext
    private EntityManager entityManager;

    public Scenic getScenicById(int id) {
        Scenic scenic = null;
        try {
            scenic = entityManager.find(Scenic.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenic;
    }
}