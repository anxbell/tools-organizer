public class Tool {
    // properties (name, category, location, isBorrowed, borrowedBy)

    private String name = "tool";
    private String category = "category";
    private String location = "location";
    private boolean isBorrowed = false;
    private String borrowedBy = null;
    
    // constructor
    public Tool(String name, String category, String location, boolean isBorrowed, String borrowedBy) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.isBorrowed = isBorrowed;
        this.borrowedBy = borrowedBy;   
    }
    
    // getters and setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public boolean isBorrowed() {
        return isBorrowed;
    }
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }
    public String getBorrowedBy() {
        return borrowedBy;
    }
    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public String toString() {

        String icon;
        if (category.equalsIgnoreCase("Hand Tool")) {
            icon = "[HAM]";   
        } else if (category.equalsIgnoreCase("Power Tool")) {
            icon = "[DRL]";   
        } else {
            icon = "[TOOL]";
        }

        String locationIcon = "[LOC]"; 

        String status = isBorrowed
                ? "[LOCK] Borrowed by: " + borrowedBy  
                : "[OK] Available";             

        return String.format(
                "%-6s %-12s | %-12s | %-6s %-10s | %s",
                icon,
                name,
                category,
                locationIcon,
                location,
                status
        );
    }

    public void borrow(String borrowedBy) {
        this.isBorrowed = true;
        this.borrowedBy = borrowedBy;
    }

    public void returnTool() {
        this.isBorrowed = false;
        this.borrowedBy = null;
    }
}