package io.returm.front.management.mission.entity;

import io.returm.front.common.web.entity.BaseEntity;

public class MissionResEntity extends BaseEntity {

    private MissionEntity mission;

    public MissionEntity getMission() {
        return mission;
    }

    public void setMission(MissionEntity mission) {
        this.mission = mission;
    }
}
