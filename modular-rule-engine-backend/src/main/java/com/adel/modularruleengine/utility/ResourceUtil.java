package com.adel.modularruleengine.utility;

import com.adel.modularruleengine.model.RuleAttributes;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.springframework.data.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ResourceUtil {

    //Gets Jar path
    public static String byGetResource(Class clazz) {
        URL classResource = clazz.getResource(clazz.getSimpleName() + ".class");
        if (classResource == null) {
            throw new RuntimeException("class resource is null");
        }
        String url = classResource.toString();
        if (url.startsWith("jar:file:")) {
            // extract 'file:......jarName.jar' part from the url string
            String path = url.replaceAll("^jar:(file:.*[.]jar)!/.*", "$1");
            try {
                return Paths.get(new URL(path).toURI()).toString();
            } catch (Exception e) {
                log.warn("Invalid Jar File URL String");
                return null;
            }
        }
        log.warn("Invalid Jar File URL String");
        return null;
    }

    public static String getJarDirectory(Class clazz){
        final String jarPath = byGetResource(clazz);
        if(null == jarPath) return null;
        final File file = new File(jarPath);
        return file.getParent();
    }

    public static List<String> readAllLine(final String filePath) throws IOException {
        final File file = new File(filePath);
        final Path path = file.toPath();

        if (!FileUtil.canReadFile(file)) return new ArrayList<>();
        return Files.readAllLines(path);
    }

    public static Map<String, Object> getFieldsAndValues(Object object){
        return Arrays.stream(RuleAttributes.class.getDeclaredFields())
                .map(m -> {
                    final Field f;
                    Object value = new Object();
                    try {
                        f = object.getClass().getDeclaredField(m.getName());
                        f.setAccessible(true);
                        value = f.get(object);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return Pair.of(m.getName(), value);
                })
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
//        Modifier.isPrivate(fields[0].getModifiers());
    }

    public static void writeAllLines(final List<String> lines, final String filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath);
        lines.forEach(newLine -> {
            try {
                fileWriter.write(newLine);
                fileWriter.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.close();
    }

}
