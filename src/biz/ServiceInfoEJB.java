package biz;

import entity.ServiceInfo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ServiceInfoEJB {

    @PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public List<ServiceInfo> getServiceInfo(int scenicId) {
        return em.createQuery("SELECT s FROM ServiceInfo s WHERE s.scenic.id = :scenicId", ServiceInfo.class)
                .setParameter("scenicId", scenicId)
                .getResultList();
    }
}
