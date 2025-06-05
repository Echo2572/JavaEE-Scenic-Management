package biz;

import entity.User;
import util.SimpleEncryption;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ResetPasswordEJB {

    @PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public void updateUserPassword(String email, String newPassword) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        
        if (user != null) {
        	String encryptedPassword=SimpleEncryption.encrypt(newPassword);
            user.setPassword(encryptedPassword);
            em.merge(user);
        }
    }
}
