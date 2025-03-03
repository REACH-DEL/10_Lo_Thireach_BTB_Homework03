abstract  public class StaffMember {
    protected int id;
    protected String name;
    protected String address;

    public StaffMember(){};
    public StaffMember(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public abstract double pay();

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return id+ "-" + name+ "-" + address;
    }
}
