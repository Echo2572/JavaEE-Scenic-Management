package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Scenic;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AllScenicEJB {
	@PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager entityManager;

    public List<Scenic> getAllScenic() {
        List<Scenic> scenicList = new ArrayList<Scenic>();
        try {
            scenicList = entityManager.createQuery("SELECT s FROM Scenic s", Scenic.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenicList;
    }
}