package driver;

public enum BrowserType {

    chrome("chrome"),
    firefox("firefox"),
    safari("safari");

    BrowserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;

}
