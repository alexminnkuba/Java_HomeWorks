import config.HibernateConfig;
import dao.PersonDao;
import entity.Person;

import java.util.List;

public class Main {
   public static void main(String[] args) {
       PersonDao dao = new PersonDao();

       System.out.println("\n=== 1. Добавляем записи ===");
       dao.save(new Person("Ольга Васильева", 29, "olga@example.com"));
       dao.save(new Person("Сергей Морозов", 41, "sergey@work.ru"));
       dao.save(new Person("Алиса Смирнова", 25, "alisa@example.com"));
       dao.save(new Person("Дмитрий Иванов", 35, "dima@work.ru"));

       System.out.println("\n=== 2. Все записи ===");
       List<Person> persons = dao.getAllPerson();
       for(Person person : persons){
           System.out.println(person);
       }

       System.out.println("\n=== Обновляем первую запись ===");
      Person p = dao.getById(1);
      if(p != null){
          p.setAge(18);
          p.setEmail("olen'ka@email.com");
          dao.update(p);
      }

       System.out.println("\n=== После обновления ===");
       List<Person> listPersons = dao.getAllPerson();
       for(Person person : listPersons){
           System.out.println(person);
       }

       System.out.println("\n=== 5. Удаляем первую запись ===");
       dao.deleteById(1);

       System.out.println("\n=== 6. Финальный список ===");
       List<Person> list_Persons = dao.getAllPerson();
       for(Person person : list_Persons){
           System.out.println(person);
       }

       HibernateConfig.shutdown();
    }
}
