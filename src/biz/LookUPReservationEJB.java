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
//        // 重新加载用户以确保会话是打开的
//        user = em.find(User.class, user.getId());
//        Set<Reservation> reservations = user.getReservations();
//
//        // 创建一个List来存储景区信息
//        List<Scenic> scenicList = new ArrayList<Scenic>();
//
//        // 遍历所有预约，获取对应的景区信息
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
        // 重新加载用户以确保会话是打开的
        user = em.find(User.class, user.getId());
        return new ArrayList<Reservation>(user.getReservations());
    }
}
