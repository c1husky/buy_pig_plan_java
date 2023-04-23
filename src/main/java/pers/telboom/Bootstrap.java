package pers.telboom;

import pers.telboom.api.ApiLoader;
import pers.telboom.driver.DriverConfig;
import pers.telboom.driver.DriverType;
import pers.telboom.impl.BaiduiShanQiaoBoomImpl;
import pers.telboom.impl.IBoom;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Bootstrap {
    public static void main(String[] args) throws Exception {
        String driverPath="D:\\tools\\driver\\chromedriver.exe";
        DriverType driverType=DriverType.CHROME;
        DriverConfig driverConfig=new DriverConfig(driverType,driverPath);
        startBoom(driverConfig);
    }
    private static void startBoom(DriverConfig driverConfig)throws Exception {
        String telephone = "13224391193";

        BoomConfig boomConfig=new BoomConfig();
        boomConfig.setTelephone(telephone);


        File apiFile=new File(FileSystemView.getFileSystemView().getHomeDirectory(),"api");

        ApiLoader apiLoader = new ApiLoader();
        apiLoader.init();
        apiLoader.load(apiFile);

        IBoom boom=new BaiduiShanQiaoBoomImpl(boomConfig)
                .init(driverConfig);
        boom.setStartApis(apiLoader.getApisByType(boom.getAccpetApiType()));
        boom.start("https://www.baidu.com");
    }
}
