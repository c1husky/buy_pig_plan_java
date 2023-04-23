package pers.telboom.impl;

import org.openqa.selenium.WebDriver;
import pers.telboom.api.Api;
import pers.telboom.BoomConfig;

public class BaiduLiXianBaoBoomImpl extends AbstractBoom {
    public BaiduLiXianBaoBoomImpl(BoomConfig boomConfig) {
        super(boomConfig);
    }

    @Override
    public Api.ApiType getAccpetApiType() {
        return null;
    }

    @Override
    public void execute(Api api, WebDriver driver) {

    }
}
