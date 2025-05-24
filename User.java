public class User {
    private String id;
    private String password;
    private String ppsn;
    private String phoneNo;

    public User() {
    }

    public User(String id, String password, String ppsn, String phoneNo) {
        this.id = id;
        this.password = password;
        this.ppsn = ppsn;
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPpsn() {
        return ppsn;
    }

    public void setPpsn(String ppsn) {
        this.ppsn = ppsn;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
