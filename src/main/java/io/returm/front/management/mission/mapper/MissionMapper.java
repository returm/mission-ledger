package io.returm.front.management.mission.mapper;

import io.returm.front.management.mission.entity.MissionEntity;

import java.util.List;

public interface MissionMapper {

    public MissionEntity get(MissionEntity entity);

    public List<MissionEntity> getList(MissionEntity entity);

    public List<MissionEntity> getAllList(MissionEntity entity);

    public int count(MissionEntity entity);

    public void insert(MissionEntity entity);

    public List<MissionEntity> getNewMissionList();

}
