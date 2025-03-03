public class SalariesEmployee extends StaffMember{
    private double salary;
    private double bonus;
    @Override
    public double pay(){
        return salary + bonus;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public SalariesEmployee(int id, String name, String address, double salary, double bonus){
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return salary+"-"+bonus;
    }
}
