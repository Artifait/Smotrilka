//package demo.project.repository;
//
//import demo.project.DTOs.RegisterRequest;
//import demo.project.DTOs.LinkRequest;
//import demo.project.model.User;
//import demo.project.model.Link;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Repository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Repository
//@Transactional
//public class Database {
//    private final Logger log = LoggerFactory.getLogger(getClass());
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public boolean registerUser(RegisterRequest request) {
//        try {
//            long exists = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.login = :login", Long.class)
//                    .setParameter("login", request.getLogin())
//                    .getSingleResult();
//
//            if (exists > 0) return false;
//
//            User user = new User(request.getLogin(), request.getPassword());
//            em.persist(user);
//            return true;
//        } catch (Exception e) {
//            log.error("registerUser failed for login={}", request == null ? "null" : request.getLogin(), e);
//            throw e; // пробрасываем, чтобы контроллер вернул 500
//        }
//    }
//
//    public boolean addLink(LinkRequest request) {
//        try {
//            User user = em.createQuery(
//                            "SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class)
//                    .setParameter("login", request.getLogin())
//                    .setParameter("password", request.getPassword())
//                    .getResultStream()
//                    .findFirst()
//                    .orElse(null);
//
//            if (user == null) return false;
//
//            Link link = new Link(request.getName(), request.getType(), request.getLink());
//            em.persist(link);
//            return true;
//        } catch (Exception e) {
//            log.error("addLink failed for login={}", request == null ? "null" : request.getLogin(), e);
//            throw e;
//        }
//    }
//}