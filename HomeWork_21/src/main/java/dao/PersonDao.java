package dao;

import config.HibernateConfig;
import entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private SessionFactory factory;

    public PersonDao() {
        this.factory = HibernateConfig.getFactory();
    }

    public void save(Person person){
        Transaction transaction = null;
        Session session = null;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(person);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public List<Person> getAllPerson(){
        Session session = null;
        try{
            session = factory.openSession();
            return session.createQuery("from Person", Person.class).list();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return new ArrayList<>();
    }

    public Person getById(int id){
        Session session = null;
        Person person = null;
        try{
            session = factory.openSession();
            person = session.get(Person.class, id);
            return person;
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return person;
    }


    public  void update(Person person){
       Transaction transaction = null;
       Session session = null;
       try{
           session = factory.openSession();
           transaction = session.beginTransaction();
           session.merge(person);
           transaction.commit();
       } catch (Exception e){
           if(session != null){
               transaction.rollback();
           }
           e.printStackTrace();
       }
       finally {
           if(session != null){
               session.close();
           }
       }
    }

    
    public void deleteById(int id){
        Transaction transaction = null;
        Session session = null;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            Person person = session.get(Person.class, id);
            if(person != null){
                session.remove(person);
            }
            transaction.commit();
        } catch (Exception e) {
            if(session != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }
}
