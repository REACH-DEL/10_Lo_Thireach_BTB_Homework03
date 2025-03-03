import java.util.*;
import java.util.regex.Pattern;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ArrayList<StaffMember> emps = new ArrayList<>();
    static int id = 8;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StaffMember volunteer1 = new Volunteer(1, "John Halane", "BTB", 250);
        StaffMember hourlyEmp1 = new HourSalaryEmployee(2, "Siv Fong", "KP", 30, 10);
        StaffMember salaryEmp1 = new SalariesEmployee(3, "Lorem Dara", "BMC", 250, 10);
        StaffMember salaryEmp2 = new SalariesEmployee(4, "Tong Sey", "SR", 350, 15);
        StaffMember volunteer2 = new Volunteer(5, "Michal Joe", "PP", 150);
        StaffMember volunteer3 = new Volunteer(6, "Teng Fa", "PVH", 300);
        StaffMember hourlyEmp2 = new HourSalaryEmployee(7, "Heng Pitu", "TK", 20, 15);
        Collections.addAll(emps, volunteer1, hourlyEmp1, salaryEmp1, salaryEmp2, volunteer2, volunteer3, hourlyEmp2);
        while (true) {
            Table tMain = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
            CellStyle cs = new CellStyle(HorizontalAlign.center);
            tMain.setColumnWidth(0, 50, 50);
            tMain.addCell(Color.BLUE.getColor("STAFF MANAGEMENT SYSTEM"), cs);
            tMain.addCell(Color.GREEN.getColor(" 1. Insert Employee"));
            tMain.addCell(Color.GREEN.getColor(" 2. Update Employee"));
            tMain.addCell(Color.GREEN.getColor(" 3. Display Employee"));
            tMain.addCell(Color.GREEN.getColor(" 4. Remove Employee"));
            tMain.addCell(Color.GREEN.getColor(" 5. Exit"));
            System.out.println(tMain.render());
            System.out.println("----------------------------------------");
            System.out.print("Choose an option: ");
            String op = sc.nextLine();
            switch (op) {
                case "1"->insert();

                case "2"->update();

                case "3"->display();

                case "4"->delete();

                case "5"->{
                    System.out.println(Color.BLUE.getColor("Thank you! Good luck🥰"));
                    return;
                }
                default -> System.out.println(Color.RED.getColor("Invalid Option!"));
            }
        }
    }

    static void display(){
        int currentPage = 1;
        System.out.println(Color.BLUE.getColor("======= * Display Employee * ======="));
        display:
        while (true){
            CellStyle cs = new CellStyle(HorizontalAlign.center);
            Table tDisplay = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            tDisplay.setColumnWidth(0, 25, 25);
            tDisplay.setColumnWidth(1, 8, 8);
            tDisplay.setColumnWidth(2, 20, 20);
            tDisplay.setColumnWidth(3, 15, 15);
            tDisplay.setColumnWidth(4, 13, 13);
            tDisplay.setColumnWidth(5, 13, 13);
            tDisplay.setColumnWidth(6, 10, 10);
            tDisplay.setColumnWidth(7, 10, 10);
            tDisplay.setColumnWidth(8, 15, 15);
            tDisplay.addCell(Color.BLUE.getColor("Type"), cs);
            tDisplay.addCell(Color.BLUE.getColor("ID"), cs);
            tDisplay.addCell(Color.BLUE.getColor("Name"), cs);
            tDisplay.addCell(Color.BLUE.getColor("Address"), cs);
            tDisplay.addCell(Color.BLUE.getColor("Salary"), cs);
            tDisplay.addCell(Color.BLUE.getColor("Bonus"), cs);
            tDisplay.addCell(Color.BLUE.getColor("Hour"), cs);
            tDisplay.addCell(Color.BLUE.getColor("Rate"), cs);
            tDisplay.addCell(Color.BLUE.getColor("Pay"), cs);
            int allPage = (emps.size()%3==0)? emps.size() / 3: emps.size()/3 +1;
            emps.stream()
                    .skip((currentPage - 1) * 3L)
                    .limit(3)
                    .forEach(emp -> {
                        String type, salary, bonus, hour, rate;

                        if (emp instanceof Volunteer) {
                            type = "Volunteer";
                            salary = "$" + emp;
                            bonus = "---";
                            hour = "---";
                            rate = "---";
                        } else if (emp instanceof SalariesEmployee) {
                            String[] dataArr = emp.toString().split("-");
                            type = "Salaried Employee";
                            salary = "$" + dataArr[0];
                            bonus = "$" + dataArr[1];
                            hour = "---";
                            rate = "---";
                        } else {
                            String[] dataArr = emp.toString().split("-");
                            type = "Hourly Salary Employee";
                            salary = "---";
                            bonus = "---";
                            hour = dataArr[0];
                            rate = "$" + dataArr[1];
                        }

                        tDisplay.addCell(Color.GREEN.getColor(type), cs);
                        tDisplay.addCell(Color.YELLOW.getColor(String.valueOf(emp.id)), cs);
                        tDisplay.addCell(Color.GREEN.getColor(emp.name), cs);
                        tDisplay.addCell(Color.GREEN.getColor(emp.address), cs);
                        tDisplay.addCell(Color.YELLOW.getColor(salary), cs);
                        tDisplay.addCell(Color.YELLOW.getColor(bonus), cs);
                        tDisplay.addCell(Color.YELLOW.getColor(hour), cs);
                        tDisplay.addCell(Color.YELLOW.getColor(rate), cs);
                        tDisplay.addCell(Color.GREEN.getColor("$" + emp.pay()), cs);
                    });
            System.out.println(tDisplay.render());
            System.out.println("\nPage: "+ currentPage +"/"+allPage);
            System.out.println("1. First Page\t\t 2. Next Page\t\t 3. Previous Page\t\t 4. Last Page\t\t 5. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.print("=> Choose: ");
            String op = sc.nextLine();
            switch (op){
                case "1" -> currentPage = 1;
                case "2" -> currentPage = (currentPage < allPage)?currentPage+1: currentPage;
                case "3" -> currentPage = (currentPage > 1)?currentPage-1:currentPage;
                case "4" -> currentPage = allPage;
                case "5" -> {break display;}
                default -> System.out.println(Color.RED.getColor("Invalid Option!"));
            }
        }
    }
    static void insert(){
        Table tType = new Table(4, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        CellStyle cs = new CellStyle(HorizontalAlign.center);
        System.out.println(Color.BLUE.getColor("======= * Insert Employee * ======="));
        type:
        while (true) {
            tType.setColumnWidth(0, 20, 20);
            tType.setColumnWidth(1, 25, 25);
            tType.setColumnWidth(2, 25, 25);
            tType.setColumnWidth(3, 15, 15);
            tType.addCell("1. Volunteer", cs);
            tType.addCell("2. Salaries Employee", cs);
            tType.addCell("3. Hourly Employee", cs);
            tType.addCell("4. Back", cs);
            System.out.println(tType.render());
            System.out.print("=> Enter type number: ");
            Scanner sc = new Scanner(System.in);
            String type = sc.nextLine();
            switch (type) {
                case "1" -> {
                    insertVolunteer();
                    break type;
                }
                case "2" -> {
                    insertSalariesEmp();
                    break type;
                }
                case "3" -> {
                    insertHourlyEmp();
                    break type;
                }
                case "4" -> {
                    break type;

                }
                default -> System.out.println(Color.RED.getColor("Invalid Type!"));
            }
        }
    }
    static void insertVolunteer(){
        System.out.println("ID: " + id);
        String name = validation("=> Enter Name: ", "^[a-zA-Z ]{1,15}$", "Name allowed only text and max 15 characters!");
        String address = validation("=> Enter Address: ", "^[a-zA-Z\\d ]{1,15}$", "Address allowed text, numbers, and max 15 characters!");
        double salary = Double.parseDouble(validation("=> Enter Salary: ", "^[1-9]\\d{0,5}(\\.\\d{1,2})?$", "Salary allowed only numbers, max 999999.99!"));
        StaffMember volunteer = new Volunteer(id, name, address, salary);
        emps.add(volunteer);
        id++;
        System.out.println(Color.GREEN.getColor("* You added " + name + " type of Volunteer successfully! *"));
    }
    static void insertSalariesEmp(){
        System.out.println("ID: " + id);
        String name = validation("=> Enter Name: ", "^[a-zA-Z ]{1,15}$", "Name allowed only text and max 15 characters!");
        String address = validation("=> Enter Address: ", "^[a-zA-Z\\d ]{1,15}$", "Address allowed text, numbers, and max 15 characters!");
        double salary = Double.parseDouble(validation("=> Enter Salary: ", "^[1-9]\\d{0,5}(\\.\\d{1,2})?$", "Salary allowed only numbers, max 999999.99!"));
        double bonus = Double.parseDouble(validation("=> Enter bonus: ", "^[1-9]\\d{0,3}(\\.\\d{1,2})?$", "Bonus allowed only numbers up to 9999.99!"));
        StaffMember volunteer = new SalariesEmployee(id, name, address, salary, bonus);
        emps.add(volunteer);
        id++;
        System.out.println(Color.GREEN.getColor("* You added " + name + " type of Salaries Employee successfully! *"));
    }
    static void insertHourlyEmp(){
        System.out.println("ID: " + id);
        String name = validation("=> Enter Name: ", "^[a-zA-Z ]{1,15}$", "Name allowed only text and max 15 characters!");
        String address = validation("=> Enter Address: ", "^[a-zA-Z\\d ]{1,15}$", "Address allowed text, numbers, and max 15 characters!");
        int hour = Integer.parseInt(validation("=> Enter Hour: ", "^[1-9]\\d{0,2}$", "Hour allowed only numbers up to 999!"));
        double rate = Double.parseDouble(validation("=> Enter Rate: ", "^[1-9]\\d{0,3}(\\.\\d{1,2})?$", "Rate allowed only numbers up to 9999.99!"));
        StaffMember volunteer = new HourSalaryEmployee(id, name, address, hour, rate);
        emps.add(volunteer);
        id++;
        System.out.println(Color.GREEN.getColor("* You added " + name + " type of Hourly Employee successfully! *"));
    }
    static void delete(){
        System.out.println(Color.BLUE.getColor("======= * Remove Employee * ======="));
        int searchID = searchID("Remove");
        emps.removeIf(e -> e.id == searchID);
        System.out.println(Color.GREEN.getColor("* ID: " + searchID + " has removed successfully! *"));
    }
    static void update(){
        System.out.println(Color.BLUE.getColor("======= * Update Employee * ======="));
        int searchedID = searchID("Update");
        emps.stream().filter(e->e.id==searchedID).forEach(e->{
            Table tSearch;
            CellStyle cs = new CellStyle(HorizontalAlign.center);
            Scanner sc = new Scanner(System.in);
            update:
            while (true) {
                if (e instanceof Volunteer) {
                    tSearch = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                    tSearch.setColumnWidth(0, 25, 25);
                    tSearch.setColumnWidth(1, 10, 10);
                    tSearch.setColumnWidth(2, 20, 20);
                    tSearch.setColumnWidth(3, 18, 18);
                    tSearch.setColumnWidth(4, 15, 15);
                    tSearch.setColumnWidth(5, 15, 15);
                    tSearch.addCell(Color.BLUE.getColor("Type"), cs);
                    tSearch.addCell(Color.BLUE.getColor("ID"), cs);
                    tSearch.addCell(Color.BLUE.getColor("Name"), cs);
                    tSearch.addCell(Color.BLUE.getColor("Address"), cs);
                    tSearch.addCell(Color.BLUE.getColor("Salary"), cs);
                    tSearch.addCell(Color.BLUE.getColor("Pay"), cs);
                    tSearch.addCell(Color.GREEN.getColor("Volunteer"), cs);
                    tSearch.addCell(Color.YELLOW.getColor(e.id+""), cs);
                    tSearch.addCell(Color.GREEN.getColor(e.name), cs);
                    tSearch.addCell(Color.GREEN.getColor(e.address), cs);
                    tSearch.addCell(Color.YELLOW.getColor("$" + e), cs);
                    tSearch.addCell(Color.GREEN.getColor("$" + e.pay()), cs);
                    System.out.println(tSearch.render());
                    System.out.println("\nChoose one column to update: ");
                    System.out.println("1. Name\t\t2. Address\t\t3. Salary\t\t0. Cancel");
                    System.out.print("=> Choose: ");
                    try {
                        int op = Integer.parseInt(sc.nextLine());
                        switch (op) {
                            case 1 -> {
                                String name = validation("=> Enter Name: ", "^[a-zA-Z ]{1,15}$", "Name allowed only text and max 15 characters!");
                                e.setName(name);
                                System.out.println(Color.GREEN.getColor("* Name has updated success fully! *"));
                            }
                            case 2 -> {
                                String address = validation("=> Enter Address: ", "^[a-zA-Z\\d ]{1,15}$", "Address allowed text, numbers, and max 15 characters!");
                                e.setAddress(address);
                                System.out.println(Color.GREEN.getColor("* Address has updated success fully! *"));
                            }

                            case 3 -> {
                                double salary = Double.parseDouble(validation("=> Enter Salary: ", "^[1-9]\\d{0,5}(\\.\\d{1,2})?$", "Salary allowed only numbers, max 999999.99!"));
                                ((Volunteer) e).setSalary(salary);
                                System.out.println(Color.GREEN.getColor("* Salary has updated success fully! *"));
                            }
                            case 0 -> {
                                break update;
                            }
                            default -> System.out.println(Color.RED.getColor("Invalid option!"));
                        }
                    } catch (NumberFormatException n) {
                        System.out.println(Color.RED.getColor("Option allowed only number!"));
                    }

                } else {
                    String[] dataArr = e.toString().split("-");
                    tSearch = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                    tSearch.setColumnWidth(0, 25, 25);
                    tSearch.setColumnWidth(1, 10, 10);
                    tSearch.setColumnWidth(2, 20, 20);
                    tSearch.setColumnWidth(3, 18, 18);
                    tSearch.setColumnWidth(4, 15, 15);
                    tSearch.setColumnWidth(5, 15, 15);
                    tSearch.setColumnWidth(6, 15, 15);
                    tSearch.addCell(Color.BLUE.getColor("Type"), cs);
                    tSearch.addCell(Color.BLUE.getColor("ID"), cs);
                    tSearch.addCell(Color.BLUE.getColor("Name"), cs);
                    tSearch.addCell(Color.BLUE.getColor("Address"), cs);
                    tSearch.addCell(Color.BLUE.getColor((e instanceof SalariesEmployee) ? "Salary" : "Hour"), cs);
                    tSearch.addCell(Color.BLUE.getColor((e instanceof SalariesEmployee) ? "Bonus" : "Rate"), cs);
                    tSearch.addCell(Color.BLUE.getColor("Pay"), cs);
                    tSearch.addCell(Color.GREEN.getColor((e instanceof SalariesEmployee) ? "Salaries Employee" : "Hourly Employee"), cs);
                    tSearch.addCell(Color.YELLOW.getColor(e.id + ""), cs);
                    tSearch.addCell(Color.GREEN.getColor(e.name),cs);
                    tSearch.addCell(Color.GREEN.getColor(e.address), cs);
                    tSearch.addCell(Color.YELLOW.getColor((e instanceof SalariesEmployee) ? "$" + dataArr[0] : dataArr[0]), cs);
                    tSearch.addCell(Color.YELLOW.getColor("$" + dataArr[1]), cs);
                    tSearch.addCell(Color.GREEN.getColor("$" + e.pay()), cs);
                    System.out.println(tSearch.render());
                    System.out.println("\nChoose one column to update: ");
                    if (e instanceof SalariesEmployee) {
                        System.out.println("1. Name\t\t2. Address\t\t3. Salary\t\t4. Bonus\t\t0. Cancel");
                        System.out.print("=> Choose: ");
                        try {
                            int op = Integer.parseInt(sc.nextLine());
                            switch (op) {
                                case 1 -> {
                                    String name = validation("=> Enter Name: ", "^[a-zA-Z ]{1,15}$", "Name allowed only text and max 15 characters!");
                                    e.setName(name);
                                    System.out.println(Color.GREEN.getColor("* Name has updated success fully! *"));
                                }
                                case 2 -> {
                                    String address = validation("=> Enter Address: ", "^[a-zA-Z\\d ]{1,15}$", "Address allowed text, numbers, and max 15 characters!");
                                    e.setAddress(address);
                                    System.out.println(Color.GREEN.getColor("* Address has updated success fully! *"));
                                }

                                case 3 -> {
                                    double salary = Double.parseDouble(validation("=> Enter Salary: ", "^[1-9]\\d{0,5}(\\.\\d{1,2})?$", "Salary allowed only numbers, max 999999.99!"));
                                    ((SalariesEmployee) e).setSalary(salary);
                                    System.out.println(Color.GREEN.getColor("* Salary has updated success fully! *"));
                                }
                                case 4 ->{
                                    double bonus = Double.parseDouble(validation("=> Enter bonus: ", "^[1-9]\\d{0,3}(\\.\\d{1,2})?$", "Bonus allowed only numbers up to 9999.99!"));
                                    ((SalariesEmployee) e).setBonus(bonus);
                                    System.out.println(Color.GREEN.getColor("* Bonus has updated success fully! *"));
                                }
                                case 0 -> {
                                    break update;
                                }
                                default -> System.out.println(Color.RED.getColor("Invalid option!"));
                            }
                        } catch (NumberFormatException n) {
                            System.out.println(Color.RED.getColor("Option allowed only number!"));
                        }
                    }else{
                        System.out.println("1. Name\t\t2. Address\t\t3. Hour\t\t4. Rate\t\t0. Cancel");
                        System.out.print("=> Choose: ");
                        try {
                            int op = Integer.parseInt(sc.nextLine());
                            switch (op) {
                                case 1 -> {
                                    String name = validation("=> Enter Name: ", "^[a-zA-Z ]{1,15}$", "Name allowed only text and max 15 characters!");
                                    e.setName(name);
                                    System.out.println(Color.GREEN.getColor("* Name has updated success fully! *"));
                                }
                                case 2 -> {
                                    String address = validation("=> Enter Address: ", "^[a-zA-Z\\d ]{1,15}$", "Address allowed text, numbers, and max 15 characters!");
                                    e.setAddress(address);
                                    System.out.println(Color.GREEN.getColor("* Address has updated success fully! *"));
                                }

                                case 3 -> {
                                    int hour = Integer.parseInt(validation("=> Enter Hour: ", "^[1-9]\\d{0,2}$", "Hour allowed only numbers up to 999!"));
                                    ((HourSalaryEmployee)e).setHourWorked(hour);
                                    System.out.println(Color.GREEN.getColor("* Hour has updated success fully! *"));
                                }
                                case 4->{
                                    double rate = Double.parseDouble(validation("=> Enter Rate: ", "^[1-9]\\d{0,3}(\\.\\d{1,2})?$", "Rate allowed only numbers up to 9999.99!"));
                                    ((HourSalaryEmployee)e).setRate(rate);
                                    System.out.println(Color.GREEN.getColor("* Rate has updated success fully! *"));
                                }
                                case 0 -> {
                                    break update;
                                }
                                default -> System.out.println(Color.RED.getColor("Invalid option!"));
                            }
                        } catch (NumberFormatException n) {
                            System.out.println(Color.RED.getColor("Option allowed only number!"));
                        }
                    }
                }
            }
        });
    }

    static int searchID(String operation){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("=> Enter or Search ID to "+operation+": ");
            try{
                int sid = Integer.parseInt(sc.nextLine());
                for(StaffMember emp: emps){
                    if(emp.id == sid){
                        return sid;
                    }
                }
                System.out.println(Color.RED.getColor("ID not found!"));
            } catch (NumberFormatException e) {
                System.out.println(Color.RED.getColor("Input allowed only number!"));
            }
        }
    }
    static String validation(String title, String regex, String errMessage){
        while (true){
            System.out.print(title);
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            boolean valid = Pattern.matches(regex, input);
            if(valid){
                if (input.trim().isEmpty()){
                    System.out.println(Color.RED.getColor("Input can't be empty!"));
                    continue;
                }
                return input;
            }
            else{
                System.out.println(Color.RED.getColor(errMessage));
            }
        }

    }
}