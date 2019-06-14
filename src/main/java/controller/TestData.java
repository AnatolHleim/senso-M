package controller;
public class TestData extends BaseTest {
    private String testCase;
    private String username;
    private String password;
    private String message;

    public String getTestCase() {
        return testCase;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getMessageText() {return message;}

    @Override
    public String toString() {
        return "TestData{" +
                "testCase='" + testCase + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", locator=" + message +
                '}';
    }
}