public class BleBase {
    //基站id
    private String id;
    //接收的信号强度
    private Integer rssi;
    public BleBase(String id, Integer rssi) {
        this.id = id;
        this.rssi = rssi;
    }

    public Integer getRssi() {
        return rssi;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }
    public double getDistance() {
        double A = 57.32;
        double n = 3.63;
        double distance;
        /*基站到终端的直线距离*/
        distance = Math.pow(10, (A - rssi)/(10*n));
        return distance;
    }

    @Override
    public String toString() {
        return "该基站ID为："+id+",信号强度值为："+rssi;
    }
}
