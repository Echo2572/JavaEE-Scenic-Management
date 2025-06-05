//package biz;
//
//import entity.Reservation;
//import entity.Scenic;
//import entity.User;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Stateless
//public class LookUPReservationEJB {
//    @PersistenceContext(unitName = "ScenicServicePlatform")
//    private EntityManager em;
//
//    public List<Scenic> getAllScenicByUser(User user) {
//        // ���¼����û���ȷ���Ự�Ǵ򿪵�
//        user = em.find(User.class, user.getId());
//        Set<Reservation> reservations = user.getReservations();
//
//        // ����һ��List���洢������Ϣ
//        List<Scenic> scenicList = new ArrayList<Scenic>();
//
//        // ��������ԤԼ����ȡ��Ӧ�ľ�����Ϣ
//        for (Reservation reservation : reservations) {
//            Scenic scenic = reservation.getScenic();
//            scenicList.add(scenic);
//        }
//
//        return scenicList;
//    }
//}











package biz;

import entity.Reservation;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LookUPReservationEJB {
    @PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public List<Reservation> getAllReservationsByUser(User user) {
        // ���¼����û���ȷ���Ự�Ǵ򿪵�
        user = em.find(User.class, user.getId());
        return new ArrayList<Reservation>(user.getReservations());
    }
}
