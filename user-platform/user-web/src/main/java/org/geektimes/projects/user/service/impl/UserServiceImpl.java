package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 用户
 *
 * @author ViJay
 * @date 2021/3/2 23:19
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

   /* private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Override
    public boolean register(User user) throws SQLException {
//        return userRepository.save(user);
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        for (ConstraintViolation<User> violation : validate) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            throw new RuntimeException(String.format("字段 [%s]，校验信息 [%s]", field, message));
        }
        user.setId(null);
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            transaction.rollback();
        }
        Query nativeQuery = entityManager.createNativeQuery("select * from users");
        List<Object[]> resultList = nativeQuery.getResultList();
        resultList.forEach(ele -> System.out.println(Arrays.toString(ele)));
        return true;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
