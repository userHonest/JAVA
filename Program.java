package Dataset02;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {


    private final static Scanner subMenuInput = new Scanner(System.in);
    private final static Scanner hoursInput = new Scanner(System.in);
    private static ArrayList<PersonData> peopleData = new ArrayList<>();

    private final String url= "jdbc:mysql://localhost:3306/timestamp";
    private final String user= "database_password_notROOT";
    private final String passw= "database_passord";

    private static Connection con;

    static {
        con = null;
    }

    {try{

        con = DriverManager.getConnection(url,user,passw);

            if ( con!= null) {
                System.out.println("---------------------------");
                System.out.println("[+] Connected To Database..");
                System.out.println("---------------------------");
                System.out.println("");
            }else {
                System.out.println("--------------------------");
                System.out.println("[-] Connection Failed...");
            }

    } catch (SQLException e) {
        e.printStackTrace();
    }



    }


    public void PersonDataMenu() throws SQLException {

        Program subMenuMethod = new Program();
        boolean loop = true;

        do {
            System.out.println("");
            System.out.println("1-.Add Data ");
            System.out.println("2-.Back to Main");

            int userInput = Integer.parseInt(subMenuInput.nextLine());

            switch (userInput) {

                case 1:
                    subMenuMethod.addHoursMenu();
                break;

                case 2:
                    loop = false;
                    break;
                default:
            }
        } while (loop == true);



    }




    /** Menu to add data to DB **/

    public void addHoursMenu() throws SQLException {

        Program setHours = new Program();
        boolean loop = true;

        do {
            System.out.println("");
            System.out.println("1.>> Add Hours");
            System.out.println("2.>> Show Data");
            System.out.println("3.>> Delete Data");
            System.out.println("4.>> Find By ID");
            System.out.println("5.>> Update Data");
            System.out.println("6.>> Back -->");

            int userInput = Integer.parseInt(hoursInput.nextLine());

            switch (userInput){

                case 1:
                        setHours.insertMethod(peopleData);
                    break;

                case 2:
                        setHours.selectData();
                    break;

                case 3:
                        setHours.deleteById(peopleData);
                    break;

                case 4:
                        setHours.showDataById(peopleData);
                    break;
                case 5:
                        setHours.updateData(peopleData);
                    break;
                case 6:
                    loop = false;
                    break;
                default:
            }
        }while (loop == true);

    }

    Statements getData = new Statements();

    public void insertMethod(ArrayList<PersonData> workData) throws SQLException {

        String sql = getData.sqlInsert();

        PreparedStatement intoTable = con.prepareStatement(sql);
        System.out.println("Add Date");
        intoTable.setInt(1, Integer.parseInt(hoursInput.nextLine()));
        System.out.println("Add Day");
        intoTable.setString(2,hoursInput.nextLine());
        System.out.println("Add Amount of Hours Worked");
        intoTable.setInt(3, Integer.parseInt(hoursInput.nextLine()));
        System.out.println("Add Extra Minutes Worked");
        intoTable.setInt(4,Integer.parseInt(hoursInput.nextLine()));

            int insertData = intoTable.executeUpdate();
            if (insertData > 0 ) {
                System.out.println("[+] Data Added");
                System.out.println("");

            }else {
                System.out.println("[-] Insert Failed");
            }

    }


    public void selectData() throws SQLException {

        String slq = getData.selectInfo();
        Statement selection = con.createStatement();
        ResultSet result = selection.executeQuery(slq);

        while (result.next()) {
            System.out.println("--------------------------------------------");
            System.out.println("Date: " + result.getInt("dato"));
            System.out.println("Day: " + result.getString("dag"));
            System.out.println("Hours Worked: " + result.getInt("tid_jobbet"));
            System.out.println("Extra Time Worked: " + result.getInt("minutt"));
            System.out.println("--------------------------------------------");
        }
    }


    public void showDataById(ArrayList<PersonData> id) throws SQLException {

        System.out.println("");
        System.out.println("Enter ID number : ");

        int number = Integer.parseInt(hoursInput.nextLine());
        Statement showData = con.createStatement();
        ResultSet result = showData.executeQuery(getData.getDataById(number));

        if (result.next()) {
            System.out.println("-------------------------------");
            System.out.println("Date: " + result.getInt("dato"));
            System.out.println("Day: " + result.getString("dag"));
            System.out.println("Hours Worked: " + result.getInt("tid_jobbet"));
            System.out.println("Extra Time Worked: " + result.getInt("minutt"));
            System.out.println("-------------------------------");
        }else {
            System.out.println("[-] Data Not Found" + peopleData);
        }
    }

    public void deleteById(ArrayList<PersonData> ids) throws SQLException {

        System.out.println("Delete ID: ");
        int id = Integer.parseInt(hoursInput.nextLine());
        Statement deleteIdData = con.createStatement();
        int result = deleteIdData.executeUpdate(getData.deleteId(id));

        if (result > 0 ) {
            System.out.println("[+] Data Deleted..");

        }else {
            System.out.println("[-] Deletion Failed..");
        }
    }


    public void updateData(ArrayList<PersonData> update) throws SQLException {

        System.out.println("[+] Update by ID, Enter ID: >>");
        int id_generate = Integer.parseInt(hoursInput.nextLine());
        String sql = "SELECT * FROM stemple WHERE time_id = " + id_generate;
        Statement updateId = con.createStatement();
        ResultSet result = updateId.executeQuery(sql);
        if (result.next()) {

            int id = result.getInt("time_id");
            int dato = result.getInt("dato");
            String dag = result.getString("dag");
            int time_work = result.getInt("tid_jobbet");
            int minutes = result.getInt("minutt");

            System.out.println("");
            System.out.println("[+] Actual Data is: ");

            System.out.println("Id: >> " + id);
            System.out.println("Date: >> " + dato);
            System.out.println("Day: >> " + dag);
            System.out.println("Hours Worked: >> " + time_work);
            System.out.println("Extra Minutes Worked: >> " + minutes);

            printOutData();

            int choice = Integer.parseInt(hoursInput.nextLine());
            String sqlStatement = "UPDATE stemple SET";

            switch (choice) {

                case 1:
                    System.out.println("Add New Date");
                    int newDate = Integer.parseInt(hoursInput.nextLine());
                    sqlStatement = sqlStatement + " dato= ? WHERE time_id = " + id_generate;
                    PreparedStatement updateDate = con.prepareStatement(sqlStatement);
                    updateDate.setInt(1,newDate );

                    int row = updateDate.executeUpdate();
                    if (row > 0) {
                        System.out.println("[+] Inserted...!");
                    }
                    break;

                case 2:
                    System.out.println("Add New Day");
                    String newDay = hoursInput.nextLine();
                    sqlStatement = sqlStatement + " dag= ? WHERE time_id = " + id_generate;
                    PreparedStatement updateDay = con.prepareStatement(sqlStatement);
                    updateDay.setString(1,newDay);

                    int rows = updateDay.executeUpdate();
                    if(rows > 0) {
                        System.out.println("[+] Inserted...!");
                    }
                    break;

                case 3:
                    System.out.println("Add Hours Worked");
                    int newTime = Integer.parseInt(hoursInput.nextLine());
                    sqlStatement = sqlStatement + " tid_jobbet= ? WHERE time_id = " + id_generate;
                    PreparedStatement updateTime = con.prepareStatement(sqlStatement);
                    updateTime.setInt(1,newTime);

                    int num = updateTime.executeUpdate();
                    if (num > 0) {
                        System.out.println("[+] Inserted ...!");

                    }
                    break;
                case 4:
                    System.out.println("Add Extra Minutes Worked");
                    int newMin = Integer.parseInt(hoursInput.nextLine());
                    sqlStatement = sqlStatement + " minutt= ? WHERE time_id = " + id_generate;
                    PreparedStatement updateMin = con.prepareStatement(sqlStatement);
                    updateMin.setInt(1,newMin);

                    int min = updateMin.executeUpdate();
                    if (min > 0) {
                        System.out.println("[+] Inserted...!");
                    }
                    break;
                default:

            }
        }else {
            System.out.println("[-] Data Not found..!");
        }
    }

    private void printOutData() {
        System.out.println("");
        System.out.println("[+] What do yu want to update? ..");
        System.out.println("1.- Date");
        System.out.println("2.- Day");
        System.out.println("3.- Hours Worked");
        System.out.println("4.- Extra Minutes Worked");
        System.out.println("-----------------------------------");
    }

}
