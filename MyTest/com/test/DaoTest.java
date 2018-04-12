package com.test;
import com.test.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

public class DaoTest {
    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction tx = null;
    @Before
    public void init() {
        config = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }
    //增加
    @Test
    public void insert() {
        UserEntity ue = new UserEntity();
        ue.setUsername("Anny");
        ue.setPassword("123");
        ue.setEmail("Anny@163.com");
        session.save(ue);
        tx.commit();
    }
    //修改
    @Test
    public void update() {
        UserEntity user = (UserEntity) session.get(UserEntity.class, new Integer(2));
        user.setUsername("Penny");
        session.update(user);
        tx.commit();
        session.close();
    }
    //查找
    @Test
    public void getById() {
        UserEntity user = (UserEntity) session.get(UserEntity.class, new Integer(8));
        tx.commit();
        session.close();
        System.out.println("ID号：" + user.getId() + "；用户名：" + user.getUsername() +
                "；密码：" + user.getPassword() + "；邮件：" + user.getEmail());
    }
    //删除
    @Test
    public void delete() {
        UserEntity user = (UserEntity) session.get(UserEntity.class, new Integer(6));
        session.delete(user);
        tx.commit();
        session.close();
    }
}

