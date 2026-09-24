
package hospital.models;

public class User {
   private int id;
   private String username;
   private String password;
   private StaffRole role;
   private Staff staff;
   private boolean active;
   
   public User(){      
   }

    public User(String username, String password, StaffRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.staff = staff;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffRole getRole() {
        return role;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

   
}
