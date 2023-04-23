package pers.telboom.api;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ApiLoader {
    private static Map<Api.ApiType, Set<Api>>apiMap=new HashMap<>();
    private static Map<Api.ApiType, ApiResolver> resolverMap =new HashMap<>();

    public Set<Api.ApiType>getApiTypes(){
        return apiMap.keySet();
    }

    public Set<Api> getApisByType(Api.ApiType apiType){
        return apiMap.get(apiType);
    }

    public void addApi(Api.ApiType apiType,Api api){
        Set<Api> apiSet = apiMap.get(apiType);
        if (apiSet==null){
            apiSet=new HashSet<>();
            apiMap.put(apiType,apiSet);
        }
        apiSet.add(api);
    }

    public void addApis(Api.ApiType apiType,Collection<Api> apis){
        Set<Api> apiSet = apiMap.get(apiType);
        if (apiSet==null){
            apiSet=new HashSet<>();
            apiMap.put(apiType,apiSet);
        }
        apis.addAll(apis);
    }


    public void register(Api.ApiType apiType,BaiduShanQiaoApiResolver resolver){
        resolverMap.put(apiType,resolver);
    }
    public void init(){
        register(Api.ApiType.BAIDU_SHAN_QIAO,new BaiduShanQiaoApiResolver());
    }
    public void load(File apiFile)  throws IOException {
        if (apiFile.isDirectory()) {
            for (File file : apiFile.listFiles()) {
                if(!file.isDirectory()){
                    String name = file.getName();
                    name=name.substring(0,name.lastIndexOf("."));
                    Api.ApiType apiType= Api.ApiType.valueOf(name.toUpperCase(Locale.ROOT));
                    ApiResolver apiResolver = resolverMap.get(apiType);
                    if (apiResolver!=null){
                        apiMap.put(apiType,apiResolver.resolve(file.toPath()));
                    }
                }
            }
        }
    }
    public void load(String apiPath) throws IOException {
        File apiFile = new File(apiPath);
        load(apiFile);
    }

}
