package controller;
public class TestData extends BaseTest {
    private String testCase;
    private String username;
    private String password;
    private String message;
    private String ownerName;
    private String managerName;
    private String engineerName;
    private String objectName;
    private String objectAddress;
    private String objectURL;
    private String snowCover;

    public String getLocator() {
        return locator;
    }

    private String locator;
    public String getEngineerName() {
        return engineerName;
    }
    public String getOwnerName() {
        return ownerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getObjectAddress() {
        return objectAddress;
    }

    public String getObjectURL() {
        return objectURL;
    }

    public String getSnowCover() {
        return snowCover;
    }



    public String getMessage() {
        return message;
    }




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
                ", message='" + message + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", objectName='" + objectName + '\'' +
                ", objectAddress='" + objectAddress + '\'' +
                ", objectURL='" + objectURL + '\'' +
                ", snowCover='" + snowCover + '\'' +
                '}';
    }
}