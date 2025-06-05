package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.User;

@Stateless
public class EditUserInfoEJB {

	@PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public boolean updateUserInfo(String username, String email, String password) {
        try {
            User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                          .setParameter("username", username)
                          .getSingleResult();
            if (user != null) {
                user.setEmail(email);
                user.setPassword(password);
                em.merge(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
