package ModelUserPhone;

public class SharedData {
    private static SharedData instance;
    private Taikhoan currentUser;

    private SharedData() {}

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public Taikhoan getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Taikhoan user) {
        this.currentUser = user;
    }
}