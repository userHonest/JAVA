package Dataset02;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    private final static Scanner selection = new Scanner(System.in);
    private static boolean continueProgram = true;

    public void MainMenuMethod() throws SQLException{

        Program programMethod = new Program();


        try(selection) {
            while (continueProgram) {

                System.out.println(" .oooooo..o           .o.           ooo        ooooo \n" +
                                   "d8P'    `Y8          .888.          `88.       .888' \n" +
                                   "Y88bo.              .8\"888.          888b     d'888  \n" +
                                   " `\"Y8888o.         .8' `888.         8 Y88. .P  888  \n" +
                                   "     `\"Y88b       .88ooo8888.        8  `888'   888  \n" +
                                   "oo     .d8P .o.  .8'     `888.  .o.  8    Y     888  \n" +
                                   "8\"\"88888P'  Y8P o88o     o8888o Y8P o8o        o888o \n" +
                                   "                                                    ");
                System.out.println(                "Student Assistant Manager v.1.0");
                System.out.println("");
                System.out.println("");
                System.out.println("-----------------------------------");
                System.out.println("1.- Enter Student Assistant Manager");
                System.out.println("2.- Exit ");

                int choice = Integer.parseInt(selection.nextLine());

                switch (choice) {

                    case 1:
                        programMethod.PersonDataMenu();
                    break;

                    case 2:
                        continueProgram = false;
                        System.out.println("--------------");
                        System.out.println("[+] Exiting...");
                    break;

                }
            }
        }catch (NumberFormatException nfe) {
            System.out.println("Invalid Option");
        }
        }


    }
