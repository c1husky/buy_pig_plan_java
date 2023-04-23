package pers.telboom.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaiduShanQiaoApiResolver implements ApiResolver{
    @Override
    public Set<Api> resolve(Path path) throws IOException {
        Set<Api> set = Files.lines(path).map(line -> {
            Api api = new Api();
            api.setUrl(line);
            return api;
        }).collect(Collectors.toSet());
        return set;
    }
}
