public class Specialist extends Graduate {
    private int score;

    public Specialist(String lastName, String firstName, int age, String speciality, String group, double rating, String subject, int score) {
        super(lastName, firstName, age, speciality, group, rating, subject);
        this.score = score;
    }

    public Specialist(Student student, String subject, int score) {
        super(student, subject);
        this.score = score;
    }

    public Specialist(Graduate graduate, int score) {
        super(graduate);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Speciality: " + super.toString() +
                " score = " + score;
    }
}
