public class Student extends Human {
    private String speciality;
    private String group;
    private double rating;

    public Student(String lastName, String firstName, int age, String speciality, String group, double rating) {
        super(lastName, firstName, age);
        this.speciality = speciality;
        this.group = group;
        this.rating = rating;
    }

    public Student( Human human,String speciality, String group, double rating) {
        super(human);
        this.speciality = speciality;
        this.group = group;
        this.rating = rating;
    }

    public Student(Student other) {
        super(other);
        this.speciality = other.speciality;
        this.group = other.group;
        this.rating = other.rating;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return  super.toString() + "speciality ='" + speciality + '\'' +
                ", group ='" + group + '\'' +
                ", rating = " + rating + ", ";
    }
}
