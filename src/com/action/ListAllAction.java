package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.test.UserEntity;
import com.util.Hibernateutil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ListAllAction extends ActionSupport {
    //使用@SessionTarget标注得到Hibernate Session
    private Session session = Hibernateutil.getSession();
    //使用@TransactionTarget标注得到Hibernate Transaction
    private Transaction transaction = session.beginTransaction();
    private List<UserEntity> users;


    public String list(){
        try{
            //得到user表中的所有记录
            users = session.createCriteria(UserEntity.class).list();
            transaction.commit();
            session.close();
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ERROR;
        }
    }
    public List<UserEntity> getUsers(){
        return users;
    }
    public void setUsers(List<UserEntity> users){
        this.users = users;
    }


}
