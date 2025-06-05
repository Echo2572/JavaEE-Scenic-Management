package biz;

import entity.User;
import util.SimpleEncryption;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRegistrationEJB {

    @PersistenceContext(unitName = "ScenicServicePlatform")
    private EntityManager em;

    public boolean registerUser(String userName, String password, String email) {
        try {
            // Check if the username already exists
            List<User> results = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", userName)
                    .getResultList();

            if (!results.isEmpty()) {
                // Username already exists
                return false;
            } else {
                // Username does not exist, proceed with registration
                String encryptedPassword = SimpleEncryption.encrypt(password);
                User newUser = new User();
                newUser.setUsername(userName);
                newUser.setPassword(encryptedPassword);
                newUser.setEmail(email);

                em.persist(newUser);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
