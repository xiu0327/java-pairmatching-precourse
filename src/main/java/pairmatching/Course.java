package pairmatching;

import java.util.Arrays;
import java.util.HashMap;

public enum Course {
    BACKEND("백엔드", "src/main/resources/backend-crew.md"),
    FRONTEND("프론트엔드", "src/main/resources/frontend-crew.md");

    private String name;
    private String filePath;

    Course(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public static Course findCourseByName(String name){
        return Arrays.stream(values())
                .filter(i -> name.equals(i.getName())).findAny().get();
    }
}
