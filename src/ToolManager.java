import java.util.ArrayList;
import java.util.List;
//This class manages a collection of tools, allowing addition, removal, searching, borrowing, and returning of tools.
public class ToolManager {
    private ArrayList<Tool> tools;
    
    public ToolManager() {
        this.tools = new ArrayList<>();
    }
    

    public void addTool(Tool tool) {
        if (tool != null) {
            tools.add(tool);
        }
    }

    public boolean removeTool(Tool tool) {
        if (tools.contains(tool)) {
            tools.remove(tool);
            return true;
        } else {
            return false;
        }
    }

    public Tool searchTool(String name, String category, String location) {
        // loop through the tools and find a match based on the info
        for (Tool tool : tools) {
            boolean nameMatches = (name == null || tool.getName().equalsIgnoreCase(name));
            boolean categoryMatches = (category == null || tool.getCategory().equalsIgnoreCase(category));
            boolean locationMatches = (location == null || tool.getLocation().equalsIgnoreCase(location));

            if (nameMatches && categoryMatches && locationMatches) {
                return tool;
            };
        }
        return null;
    }

    public List<Tool> getAllTools() {
        return tools;
    }

    public void displayAllTools() {
        if (tools.isEmpty()) {
            System.out.println("The tool organizer is empty.");
        }

        for (Tool tool : tools) {
            System.out.println(tool);
        }
    }

    public boolean borrowTool(Tool tool, String borrowedBy) {
        if (tool != null && !tool.isBorrowed()) {
            tool.borrow(borrowedBy);
            return true;
        } else {
            return false;
        }
    }

    public boolean returnTool(Tool tool, String borrowedBy) {
        if (tool != null && tool.isBorrowed()) {
            tool.returnTool();
            return true;
        } else {
            return false;
        }
    }

}