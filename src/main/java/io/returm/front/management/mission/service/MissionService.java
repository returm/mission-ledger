package io.returm.front.management.mission.service;

import io.returm.front.management.charge.entity.RewardEntity;
import io.returm.front.management.charge.mapper.RewardMapper;
import io.returm.front.management.common.entity.FileEntity;
import io.returm.front.management.common.service.FileService;
import io.returm.front.management.mission.entity.MissionCommonEntity;
import io.returm.front.management.mission.entity.MissionEntity;
import io.returm.front.management.mission.mapper.MissionCommonMapper;
import io.returm.front.management.mission.mapper.MissionMapper;
import io.returm.front.management.mission.mapper.RecomMissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private Logger logger = LoggerFactory.getLogger(MissionService.class);

    @Autowired
    private MissionMapper missionMapper;

    @Autowired
    private RecomMissionMapper recomMissionMapper;

    @Autowired
    private MissionCommonMapper missionCommonMapper;

    @Autowired
    private RewardMapper rewardMapper;

    @Autowired
    private FileService fileService;

    public List<RewardEntity> getMyRewardList(RewardEntity entity) {
        int count = rewardMapper.count(entity);
        entity.getPagingValue(count);

        return rewardMapper.getList(entity);
    }

    public MissionCommonEntity getMissionCommon() {
        MissionCommonEntity commonEntity = missionCommonMapper.get();
        commonEntity.setContents(commonEntity.getContents().replace("\n", "<br/>"));
        return commonEntity;
    }

    public MissionEntity getMission(MissionEntity entity) {
        MissionEntity missionEntity = missionMapper.get(entity);
        missionEntity.setContents(missionEntity.getContents().replace("\n", "<br/>"));
        return missionEntity;
    }

    public List<MissionEntity> getMissionList(MissionEntity entity) {
        int count = missionMapper.count(entity);
        entity.getPagingValue(count);

        List<MissionEntity> resultList = missionMapper.getList(entity);

        for(MissionEntity missionEntity : resultList) {

            if(missionEntity!=null && org.apache.commons.lang3.StringUtils.isNotBlank(missionEntity.getImageFileSq())) {
                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileSq(missionEntity.getImageFileSq());
                fileEntity = fileService.get(fileEntity);
                missionEntity.setFileEntity(fileEntity);
            }

            missionEntity.setContents(missionEntity.getContents().replace("\n", "<br/>"));

        }

        return resultList;
    }

    public List<MissionEntity> getRecommendMissionList() {

        List<MissionEntity> resultList = recomMissionMapper.getRecomMissionList();
        for(MissionEntity missionEntity : resultList) {
            if(missionEntity!=null && org.apache.commons.lang3.StringUtils.isNotBlank(missionEntity.getImageFileSq())) {
                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileSq(missionEntity.getImageFileSq());
                fileEntity = fileService.get(fileEntity);
                missionEntity.setFileEntity(fileEntity);
            }

            missionEntity.setContents(missionEntity.getContents().replace("\n", "<br/>"));
        }

        return resultList;
    }

    public List<MissionEntity> getNewMissionList() {

        List<MissionEntity> resultList = missionMapper.getNewMissionList();
        for(MissionEntity missionEntity : resultList) {
            if(missionEntity!=null && org.apache.commons.lang3.StringUtils.isNotBlank(missionEntity.getImageFileSq())) {
                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileSq(missionEntity.getImageFileSq());
                fileEntity = fileService.get(fileEntity);
                missionEntity.setFileEntity(fileEntity);
            }

            missionEntity.setContents(missionEntity.getContents().replace("\n", "<br/>"));
        }
        return resultList;
    }


    public void insertMission(MissionEntity entity) {
        missionMapper.insert(entity);
    }
}
