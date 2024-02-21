package hw4;

public record Employee(int personnelNumber, String phoneNumber, String name, int experience) {

    @Override
    public String toString() {
        return "personnelNumber - " + personnelNumber +
                ", phoneNumber - " + phoneNumber +
                ", name - " + name +
                ", experience - " + experience;
    }
}
