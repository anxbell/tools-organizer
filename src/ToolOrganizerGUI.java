import javax.swing.*;
import java.awt.*;
// https://www.geeksforgeeks.org/java/introduction-to-java-swing/
// https://www.tutorialspoint.com/swing/swing_controls.htm
public class ToolOrganizerGUI extends JFrame {
    private ToolManager toolManager;
    private FileManager fileManager;
    
    // Componentes
    private JTextField toolNameField;
    private JTextField toolCategoryField;
    private JTextField toolLocationField;
    private JTextField toolBorrowedByField;
    private JCheckBox toolBorrowedCheckBox;
    private JTextArea toolListArea;
    
    public ToolOrganizerGUI() {
        // existing classes setup
        toolManager = new ToolManager();
        fileManager = new FileManager("data/tools.csv");
        
        // Load data from file on startup
        fileManager.loadFromFile(toolManager);
        
        setupGUI();
    }
    
    private void setupGUI() {
        setTitle("Tool Organizer");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
       // Create and add panels
        add(createInputPanel(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
        add(createDisplayPanel(), BorderLayout.SOUTH);
        
        // Show tools on startup
        refreshToolList();
        
        setLocationRelativeTo(null);
    }

    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Tool Information"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Tool Name:"));
        toolNameField = new JTextField();
        panel.add(toolNameField);
        
        panel.add(new JLabel("Category:"));
        toolCategoryField = new JTextField();
        panel.add(toolCategoryField);
        
        panel.add(new JLabel("Location:"));
        toolLocationField = new JTextField();
        panel.add(toolLocationField);
        
        panel.add(new JLabel("Is Borrowed:"));
        toolBorrowedCheckBox = new JCheckBox();
        panel.add(toolBorrowedCheckBox);
        
        panel.add(new JLabel("Borrowed By:"));
        toolBorrowedByField = new JTextField();
        panel.add(toolBorrowedByField);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Buttons based on the console menu
        JButton addButton = new JButton("1. Add Tool");
        JButton removeButton = new JButton("2. Remove Tool");
        JButton searchButton = new JButton("3. Search Tool");
        JButton displayButton = new JButton("4. Display All");
        JButton borrowButton = new JButton("5. Borrow Tool");
        JButton returnButton = new JButton("6. Return Tool");
        JButton saveButton = new JButton("7. Save to File");
        JButton clearButton = new JButton("Clear Fields");
        JButton exitButton = new JButton("8. Exit");
        
        // Connect events (same as your switch in console)
        addButton.addActionListener(e -> addTool());
        removeButton.addActionListener(e -> removeTool());
        searchButton.addActionListener(e -> searchTool());
        displayButton.addActionListener(e -> refreshToolList());
        borrowButton.addActionListener(e -> borrowTool());
        returnButton.addActionListener(e -> returnTool());
        saveButton.addActionListener(e -> saveToFile());
        clearButton.addActionListener(e -> clearFields());
        exitButton.addActionListener(e -> exitApp());
        
        // Add all buttons
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(searchButton);
        panel.add(displayButton);
        panel.add(borrowButton);
        panel.add(returnButton);
        panel.add(saveButton);
        panel.add(clearButton);
        panel.add(exitButton);
        
        return panel;
    }
    
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tool List"));
        
        toolListArea = new JTextArea(15, 50);
        toolListArea.setEditable(false);
        toolListArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(toolListArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // switch cases methods (gathered from the console app)
    
    // Case 1: Add Tool
    private void addTool() {
        String name = toolNameField.getText().trim();
        String category = toolCategoryField.getText().trim();
        String location = toolLocationField.getText().trim();
        boolean isBorrowed = toolBorrowedCheckBox.isSelected();
        String borrowedBy = toolBorrowedByField.getText().trim();
        
        // valitation for name
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a tool name",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // If marked as borrowed but no name
        if (isBorrowed && borrowedBy.isEmpty()) {
            borrowedBy = "Unknown";
        }
        
        // Create and add tool  
        Tool newTool = new Tool(name, category, location, isBorrowed, 
                               isBorrowed ? borrowedBy : null);
        toolManager.addTool(newTool);
        
        JOptionPane.showMessageDialog(this, "Tool added successfully!");
        clearFields();
        refreshToolList();
    }
    
    // Case 2: Remove Tool
    private void removeTool() {
        String removeName = JOptionPane.showInputDialog(this,
            "Enter tool name to remove:");
        
        if (removeName != null && !removeName.trim().isEmpty()) {
            Tool toolToRemove = toolManager.searchTool(removeName, null, null);
            if (toolToRemove != null) {
                toolManager.removeTool(toolToRemove);
                JOptionPane.showMessageDialog(this, "Tool removed successfully!");
                refreshToolList();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Tool not found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Case 3: Search Tool
    private void searchTool() {
        String searchName = JOptionPane.showInputDialog(this,
            "Enter tool name to search:");
        
        if (searchName != null && !searchName.trim().isEmpty()) {
            Tool foundTool = toolManager.searchTool(searchName, null, null);
            if (foundTool != null) {
                toolListArea.setText("=== SEARCH RESULT ===\n\n" + foundTool.toString());
                
                // Fill input fields with found tool data
                toolNameField.setText(foundTool.getName());
                toolCategoryField.setText(foundTool.getCategory());
                toolLocationField.setText(foundTool.getLocation());
                toolBorrowedCheckBox.setSelected(foundTool.isBorrowed());
                toolBorrowedByField.setText(foundTool.getBorrowedBy() != null ? 
                                           foundTool.getBorrowedBy() : "");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Tool not found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Case 4 Display All Tools
    private void refreshToolList() {
        toolListArea.setText("");
        toolListArea.append("========== ALL TOOLS ==========\n\n");
        
        // using the manager to get all tools
        java.util.List<Tool> tools = toolManager.getAllTools();
        
        if (tools.isEmpty()) {
            toolListArea.append("No tools in the organizer.\n");
        } else {
            for (Tool tool : tools) {
                toolListArea.append(tool.toString() + "\n");
                toolListArea.append("-".repeat(50) + "\n");
            }
        }
    }
    
    // Case 5 Borrow Tool
    private void borrowTool() {
        String borrowName = JOptionPane.showInputDialog(this,
            "Enter tool name to borrow:");
        
        if (borrowName != null && !borrowName.trim().isEmpty()) {
            Tool toolToBorrow = toolManager.searchTool(borrowName, null, null);
            
            if (toolToBorrow != null && !toolToBorrow.isBorrowed()) {
                String borrowerName = JOptionPane.showInputDialog(this,
                    "Enter your name:");
                
                if (borrowerName != null && !borrowerName.trim().isEmpty()) {
                    toolManager.borrowTool(toolToBorrow, borrowerName);
                    JOptionPane.showMessageDialog(this, 
                        "Tool borrowed successfully!");
                    refreshToolList();
                }
            } else if (toolToBorrow != null && toolToBorrow.isBorrowed()) {
                JOptionPane.showMessageDialog(this,
                    "Tool is already borrowed by: " + toolToBorrow.getBorrowedBy(),
                    "Not Available",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Tool not found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Case 6 return tool
    private void returnTool() {
        String returnName = JOptionPane.showInputDialog(this,
            "Enter tool name to return:");
        
        if (returnName != null && !returnName.trim().isEmpty()) {
            Tool toolToReturn = toolManager.searchTool(returnName, null, null);
            
            if (toolToReturn != null && toolToReturn.isBorrowed()) {
                String returnerName = JOptionPane.showInputDialog(this,
                    "Enter your name:");
                
                if (returnerName != null && !returnerName.trim().isEmpty()) {
                    toolManager.returnTool(toolToReturn, returnerName);
                    JOptionPane.showMessageDialog(this, 
                        "Tool returned successfully!");
                    refreshToolList();
                }
            } else if (toolToReturn != null && !toolToReturn.isBorrowed()) {
                JOptionPane.showMessageDialog(this,
                    "This tool is not currently borrowed",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Tool not found",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // case 7 save to File
    private void saveToFile() {
        fileManager.saveToFile(toolManager);
        JOptionPane.showMessageDialog(this, 
            "Tools saved to file successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    //case 8 exit
    private void exitApp() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Do you want to save before exiting?",
            "Exit Confirmation",
            JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            saveToFile();
            System.exit(0);
        } else if (confirm == JOptionPane.NO_OPTION) {
            System.exit(0);
        }

    }
    
    // clear the fields function
    private void clearFields() {
        toolNameField.setText("");
        toolCategoryField.setText("");
        toolLocationField.setText("");
        toolBorrowedByField.setText("");
        toolBorrowedCheckBox.setSelected(false);
    }
}