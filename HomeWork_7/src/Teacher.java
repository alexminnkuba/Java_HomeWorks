public class Teacher extends Human {
    private String speciality;
    private int experience;

    public Teacher(String lastName, String firstName, int age, String speciality, int experience) {
        super(lastName, firstName, age);
        this.speciality = speciality;
        this.experience = experience;
    }

    public Teacher(Human human, String speciality, int experience) {
        super(human);
        this.speciality = speciality;
        this.experience = experience;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "speciality = '" + speciality + '\'' +
                ", experience = " + experience;
    }
}
