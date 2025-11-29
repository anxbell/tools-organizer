// import java.util.ArrayList;
import java.util.Scanner;

public class ToolOrganizerApp {

    static Scanner scanner = new Scanner(System.in);
    // public static void main(String[] args) {
        
    //     ToolManager manager = new ToolManager();
    //     FileManager fileManager = new FileManager("data/tools.csv");
    //     // call the menu method
    //     menu(manager, fileManager);

    //     // manager.addTool(
    //     //     new Tool(
    //     //         "Hammer", 
    //     //         "Hand Tool", 
    //     //         "Garage",
    //     //         false, 
    //     //         null));
    //     //
    //     // manager.addTool(
    //     //     new Tool(
    //     //         "Drill", 
    //     //         "Power Tool", 
    //     //         "Shed", 
    //     //         true, 
    //     //         "Alice"));
    //     //
    //     // manager.addTool(
    //     //     new Tool(
    //     //         "Screwdriver", 
    //     //         "Hand Tool", 
    //     //         "Toolbox", 
    //     //         false, 
    //     //         null));
    //     // 
    //     // String searchName = "Drill";
    //     // String searchCategory = "Power Tool"; 
    //     // String searchLocation = "Shed"; 
    //     //
    //     // System.out.println("Searching for tool: " + searchName);
    //     // Tool foundTool = manager.searchTool(searchName, searchCategory, searchLocation);
    //     // if (foundTool != null) {
    //     //     System.out.println("Tool found: " + foundTool);
    //     // } else {
    //     //     System.out.println("Tool not found.");
    //     // }
    //     //
    //     // // display all tools
    //     // System.out.println("\nAll tools in the organizer:");
    //     // manager.displayAllTools();
    //     //
    //     //
    //     // // borrow a tool
    //     // System.out.println("\nBorrowing the Hammer:");
    //     //
    //     // Tool hammer = manager.searchTool("Hammer", "Hand Tool", "Garage");
    //     //
    //     // manager.borrowTool(hammer, "Bob");
    //     // // display all tools after borrowing
    //     // System.out.println("\nAll tools after borrowing the Hammer:");
    //     // manager.displayAllTools();
    //     //
    //     // // return a tool
    //     // System.out.println("\nReturning the Drill:");
    //     // Tool drill = manager.searchTool("Drill", "Power Tool", "Shed");
    //     // if (drill != null && drill.isBorrowed()) {
    //     //     manager.returnTool(drill, "Alice");
    //     // }
    //     //
    //     // // display all tools after returning the Drill:
    //     // System.out.println("\nAll tools after returning the Drill:");
    //     // manager.displayAllTools();
    //     //
    //     // // remove a tool
    //     // System.out.println("\nRemoving the Screwdriver:");
    //     // Tool screwdriver = manager.searchTool("Screwdriver", "Hand Tool", "Toolbox");
    //     // manager.removeTool(screwdriver);
    //     //
    //     // // display all tools after removal
    //     // System.out.println("\nAll tools after removing the Screwdriver:");
    //     // manager.displayAllTools();

    //     // close scanner
    //     scanner.close();
    // }
    public static void startConsoleMode() {
        ToolManager manager = new ToolManager();
        FileManager fileManager = new FileManager("data/tools.csv");
        menu(manager, fileManager);
        scanner.close();
    }

    public static void pause() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();  
    }

    public static void addTestData(ToolManager manager) {
        manager.addTool(
            new Tool(
                "Hammer", 
                "Hand Tool", 
                "Garage",
                false, 
                null));
        
        manager.addTool(
            new Tool(
                "Drill", 
                "Power Tool", 
                "Shed", 
                true, 
                "Alice"));
        
        manager.addTool(
            new Tool(
                "Screwdriver", 
                "Hand Tool", 
                "Toolbox", 
                false, 
                null));
    }

    // menu method moved outside main
    private static void menu(ToolManager manager, FileManager fileManager) {
        // addTestData(manager);
        fileManager.loadFromFile(manager);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Tool Organizer App!");
            System.out.println("===== TOOL ORGANIZER =====");
            System.out.println("1. Add Tool");
            System.out.println("2. Remove Tool");
            System.out.println("3. Search Tool");
            System.out.println("4. Display All Tools");
            System.out.println("5. Borrow Tool");
            System.out.println("6. Return Tool");
            System.out.println("7. Save Tools to File");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Add Tool
                    System.out.print("Enter tool name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter tool category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter tool location: ");
                    String location = scanner.nextLine();
                    manager.addTool(new Tool(name, category, location, false, null));
                    System.out.println("Tool added.");
                    pause();
                    break;
                case 2:
                    // Remove Tool
                    System.out.print("Enter tool name to remove: ");
                    String removeName = scanner.nextLine();
                    Tool toolToRemove = manager.searchTool(removeName, null, null);
                    if (toolToRemove != null) {
                        manager.removeTool(toolToRemove);
                        System.out.println("Tool removed.");
                    } else {
                        System.out.println("Tool not found.");
                    }
                    pause();
                    break;
                case 3:
                    // Search Tool
                    System.out.print("Enter tool name to search: ");
                    String searchName = scanner.nextLine();
                    Tool foundTool = manager.searchTool(searchName, null, null);
                    if (foundTool != null) {
                        System.out.println("\nTool found: " + foundTool);
                    } else {
                        System.out.println("Tool not found.");
                    }
                    pause();
                    break;
                case 4:
                    // Display All Tools
                    manager.displayAllTools();
                    pause();
                    break;
                case 5:
                    // Borrow Tool
                    System.out.print("Enter tool name to borrow: ");
                    String borrowName = scanner.nextLine();
                    Tool toolToBorrow = manager.searchTool(borrowName, null, null);
                    if (toolToBorrow != null && !toolToBorrow.isBorrowed()) {
                        System.out.print("Enter your name: ");
                        String borrowerName = scanner.nextLine();
                        manager.borrowTool(toolToBorrow, borrowerName);
                        System.out.println("Tool borrowed successfully.");
                    } else {
                        System.out.println("Tool not available for borrowing.");
                    }
                    pause();
                    break;
                case 6:
                    // Return Tool
                    System.out.print("Enter tool name to return: ");
                    String returnName = scanner.nextLine();
                    Tool toolToReturn = manager.searchTool(returnName, null, null);
                    if (toolToReturn != null && toolToReturn.isBorrowed()) {
                        System.out.print("Enter your name: ");
                        String returnerName = scanner.nextLine();
                        manager.returnTool(toolToReturn, returnerName);
                        System.out.println("Tool returned successfully.");
                    } else {
                        System.out.println("Tool not available for returning.");
                    }
                    pause();
                    break;
        
                case 7:
                    // Save Tools to File
                    fileManager.saveToFile(manager);
                    System.out.println("Tools saved to file.");
                    pause();
                    break;
                case 8:
                    // Exit
                    running = false;
                    System.out.println("Thank you. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
    }

}