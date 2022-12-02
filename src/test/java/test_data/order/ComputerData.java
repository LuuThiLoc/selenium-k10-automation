package test_data.order;

public class ComputerData {

    public ComputerData(String processorType, String ram, String os, String hdd, String software) {
        this.processorType = processorType;
        this.ram = ram;
        this.os = os;
        this.hdd = hdd;
        this.software = software;
    }

    public String getProcessorType() {
        return processorType;
    }

    public String getRam() {
        return ram;
    }

    public String getOs() {
        return os;
    }

    public String getHdd() {
        return hdd;
    }

    public String getSoftware() {
        return software;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public void setSoftware(String software) {
        this.software = software;
    }
    private String processorType;
    private String ram;
    private String os;
    private String hdd;
    private String software;

    @Override
    public String toString() {
        return "ComputerData{" +
                "processorType='" + processorType + '\'' +
                ", ram='" + ram + '\'' +
                ", os='" + os + '\'' +
                ", hdd='" + hdd + '\'' +
                ", software='" + software + '\'' +
                '}';
    }
}
