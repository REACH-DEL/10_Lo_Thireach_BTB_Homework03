public class Volunteer extends StaffMember{
    private double salary;
    @Override
    public double pay(){
        return salary;
    }

    public Volunteer(int id, String name, String address, double salary){
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.valueOf(salary);

    }
}
