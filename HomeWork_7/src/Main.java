void main() {
    Human human = new Human("Батодалаев", "Даши", 16);
    Student student1 = new Student(human, "ГК", "Web_011", 5);
    System.out.println(student1);

    Student student = new Student("Загидуллин", "Линар", 32, "РПО", "PD_001", 5);
    System.out.println(student);

    Student student2 = new Student("Шугани", "Сергей", 15, "РПО", "PD_011", 5);
    Graduate graduate = new Graduate(student2, "Защита персональных данных");
    System.out.println(graduate);



    Teacher teacher = new Teacher("Даньшин", "Андрей", 38, "Астрофизика", 110);
    System.out.println(teacher);

    Student student3 = new Student("Маркин", "Даниил", 17, "ГК", "Python_011", 5);
    System.out.println(student3);

    Human human1 = new Human("Башкиров", "Алексей", 45);
    Teacher teacher1 = new Teacher(human1, "Разработка приложений", 20);
    System.out.println(teacher1);

    Specialist specialist = new Specialist(graduate, 12);
    System.out.println(specialist);
}
