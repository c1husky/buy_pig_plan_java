package pers.telboom.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public interface ApiResolver {
    Set<Api> resolve(Path path) throws IOException;
}
