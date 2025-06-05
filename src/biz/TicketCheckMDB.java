//package biz;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//import javax.annotation.Resource;
//import javax.ejb.MessageDrivenContext;
//import javax.sql.DataSource;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//
//@MessageDriven(activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/ticketCheckQueue")
//})
//public class TicketCheckMDB implements MessageListener {
//    @Resource
//    private MessageDrivenContext mdc;
//
//    @Resource(lookup = "java:jboss/datasources/ScenicJPA")
//    private DataSource dataSource;
//
//    public TicketCheckMDB() {
//    }
//
//    @Override
//    public void onMessage(Message inMessage) {
//        TextMessage msg = null;
//        Connection connection = null;
//        PreparedStatement checkStmt = null;
//        PreparedStatement updateStmt = null;
//
//        try {
//            if (inMessage instanceof TextMessage) {
//                msg = (TextMessage) inMessage;
//                String ticketCode = msg.getText();
//                System.out.println("Received ticket code: " + ticketCode);
//
//                connection = dataSource.getConnection();
//
//                // 检查票是否有效
//                String checkSql = "SELECT * FROM reservation WHERE ticket_code = ?";
//                checkStmt = connection.prepareStatement(checkSql);
//                checkStmt.setString(1, ticketCode);
//                ResultSet rs = checkStmt.executeQuery();
//
//                if (rs.next()) {
//                	// 获取当前 visitTime 值
//                    Timestamp originalVisitTime = rs.getTimestamp("visit_time");
//                    System.out.println("Original visitTime: " + originalVisitTime);
//                    
//                    String updateSql = "UPDATE reservation SET status = 'CHECKED_IN', visit_time = ? WHERE ticket_code = ?";
//                    updateStmt = connection.prepareStatement(updateSql);
//                    updateStmt.setTimestamp(1, originalVisitTime);  // 保留原始的 visitTime 值
//                    updateStmt.setString(2, ticketCode);
//                    updateStmt.executeUpdate();
//                    System.out.println("Ticket checked in successfully: " + ticketCode);
//                } else {
//                    System.out.println("Invalid ticket code: " + ticketCode);
//                }
//            } else {
//                System.out.println("Message of wrong type: " + inMessage.getClass().getName());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            mdc.setRollbackOnly();
//        } finally {
//            try {
//                if (checkStmt != null) {
//                    checkStmt.close();
//                }
//                if (updateStmt != null) {
//                    updateStmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}




package biz;

import entity.Reservation;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Timestamp;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/ticketCheckQueue")
})
public class TicketCheckMDB implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;

    @PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public TicketCheckMDB() {
    }

    @Override
    public void onMessage(Message inMessage) {
        TextMessage msg = null;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                String ticketCode = msg.getText();
                System.out.println("Received ticket code: " + ticketCode);

                // 检查票是否有效
                Reservation reservation = em.createQuery("SELECT r FROM Reservation r WHERE r.ticketCode = :ticketCode", Reservation.class)
                                            .setParameter("ticketCode", ticketCode)
                                            .getSingleResult();

                if (reservation != null) {
                    // 获取当前 visitTime 值
                    Timestamp originalVisitTime = reservation.getVisitTime();
                    System.out.println("Original visitTime: " + originalVisitTime);

                    reservation.setStatus("CHECKED_IN");
                    // 保留原始的 visitTime 值
                    reservation.setVisitTime(originalVisitTime);

                    em.merge(reservation);
                    System.out.println("Ticket checked in successfully: " + ticketCode);
                } else {
                    System.out.println("Invalid ticket code: " + ticketCode);
                }
            } else {
                System.out.println("Message of wrong type: " + inMessage.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        }
    }
}

