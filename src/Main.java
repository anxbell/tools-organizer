import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        do {
            System.out.println("========================================");
            System.out.println("   TOOL ORGANIZER   ");
            System.out.println("========================================");
            System.out.println("1. Console Mode");
            System.out.println("2. GUI Mode");
            System.out.print("Select mode (1 or 2): ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                
                if (choice != 1 && choice != 2) {
                    System.out.println("\nInvalid option. Please choose 1 or 2.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number (1 or 2).\n");
                choice = 0; 
            }
            //  scanner.close();
            
        } while (choice != 1 && choice != 2);
        
        // based on user choice display 
        switch (choice) {
            case 1:
                System.out.println("\nStarting Console Mode...\n");
                ToolOrganizerApp.startConsoleMode();
                break;
                
            case 2:
                System.out.println("\nStarting GUI Mode...\n");
                scanner.close();
                SwingUtilities.invokeLater(() -> {
                    ToolOrganizerGUI gui = new ToolOrganizerGUI();
                    gui.setVisible(true);
                });
                break;
        }
    }
}
