
enum Speed {
    CAR("65"),
    TRUCK("55"),
    AIRPLANE("600"),
    TRAIN("70"),
    BOAT("22");

    private final String speed;

    Speed(String speed) {
    	this.speed = speed;

    	 }

    @Override
    public String toString() {
        return this.name() + " типичная скорость составляет " + speed + " миль в час.";
    }
}
void main() {
    System.out.println("\nСкорость самолета составляет " + Speed.AIRPLANE.speed + " миль в час.\n");
    System.out.println("Скорости транспортных средств: ");
    for(Speed speed: Speed.values()){
     System.out.println(speed);
    }
}
