public class Graduate extends  Student{
    private String subject;

    public Graduate(String lastName, String firstName, int age, String speciality, String group, double rating, String subject) {
        super(lastName, firstName, age, speciality, group, rating);
        this.subject = subject;
    }

    public Graduate(Student student, String subject) {
        super(student);
        this.subject = subject;
    }


    public Graduate(Graduate graduate) {
        super(graduate);
        this.subject = graduate.subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "subject = '" + subject + '\'';
    }
}
