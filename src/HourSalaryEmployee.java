public class HourSalaryEmployee extends StaffMember{
    private int hourWorked;
    private double rate;
    @Override
    public double pay(){
        return hourWorked * rate;
    }

    public HourSalaryEmployee(int id, String name, String address, int hourWorked, double rate){
        this.id = id;
        this.name = name;
        this.address = address;
        this.hourWorked = hourWorked;
        this.rate = rate;
    }

    public void setHourWorked(int hourWorked) {
        this.hourWorked = hourWorked;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return hourWorked+"-"+rate;
    }
}
