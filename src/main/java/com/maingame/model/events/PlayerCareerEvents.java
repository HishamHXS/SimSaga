package com.maingame.model.events;

import com.maingame.model.career.Job;
import com.maingame.model.career.PlayerCareer;
import com.maingame.model.career.Student;
import com.maingame.model.Player;
import com.maingame.service.CareerService;

public class PlayerCareerEvents {

    private final Player player;
    private final CareerService careerService;

    public PlayerCareerEvents(Player player) {
        this.player = player;
        this.careerService = new CareerService();
    }

    public void playerYearlySalary() {
        PlayerCareer playerCareer = this.player.getPlayerCareer();

        if (playerCareer.getCurrentCareerStatus() == PlayerCareer.PlayerCareerStatus.EMPLOYED) {
            Job playerJob = (Job) playerCareer.getCurrentCareer();
            this.player.updateCash(playerJob.getSalary());
        }
    }

    public void playerSchoolUpdate() {
        if (this.player.getAge() == 6) {
            updateSchool("Elementary School", Student.StudentType.ELEMENTARYSCHOOL);
        } else if (this.player.getAge() == 11) {
            updateSchool("Middle School", Student.StudentType.MIDDLESCHOOL);
        } else if (this.player.getAge() == 16) {
            updateSchool("High School", Student.StudentType.HIGHSCHOOL);
        }
    }

    private void updateSchool(String school, Student.StudentType studentType) {
        Student career = new Student(school,  studentType, careerService.getSchoolName(studentType));
        this.player.getPlayerCareer().updateCurrentCareer(career);
    }
}
