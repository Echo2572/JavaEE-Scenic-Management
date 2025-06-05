package biz;

import entity.User;
import util.SimpleEncryption;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserLoginEJB {

    @PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public User login(String username, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();

        if (!users.isEmpty()) {
            User user = users.get(0);
            String decryptedPassword = SimpleEncryption.decrypt(user.getPassword());

            if (decryptedPassword.equals(password)) {
                return user;
            }
        }
        return null;
    }
}
