import java.io.Serializable;

public class Location implements Serializable {
    private Double xAxis;
    private Double yAxis;

    public Location(Double xAxis, Double yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }
    public Location() {
        super();
    }
    public Double getxAxis() {
        return xAxis;
    }

    public void setxAxis(Double xAxis) {
        this.xAxis = xAxis;
    }

    public Double getyAxis() {
        return yAxis;
    }

    public void setyAxis(Double yAxis) {
        this.yAxis = yAxis;
    }

    @Override
    public String toString() {
        return  "位置是" + xAxis + "," + yAxis;
    }

}
