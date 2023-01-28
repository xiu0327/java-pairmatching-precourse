package pairmatching.dto;

import pairmatching.Course;
import pairmatching.Level;

public class PairDto {

    private String input;
    private Course course;
    private Level level;
    private String mission;


    public PairDto(String input) {
        this.input = input;
        String[] options = input.split(", ");
        course = Course.findCourseByName(options[0]);
        level = Level.findLevelByName(options[1]);
        mission = options[2];
        checkInputMission();
    }

    public void checkInputMission(){
        if(!level.getMissions().contains(mission)) throw new IllegalStateException();
    }

    public String getInput() {
        return input;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
}
