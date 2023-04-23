package pers.telboom.driver;

/**
 * @description: 驱动配置
 **/
public class DriverConfig {
    private DriverType driverType;
    //驱动所在路径
    private String driverPath;

    public DriverConfig(DriverType driverType, String driverPath) {
        this.driverType = driverType;
        this.driverPath = driverPath;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public String getDriverPath() {
        return driverPath;
    }
}
