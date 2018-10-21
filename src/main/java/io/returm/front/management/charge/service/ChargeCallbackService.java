package io.returm.front.management.charge.service;

import io.returm.front.common.exception.DuplicateRewardException;
import io.returm.front.management.charge.entity.*;
import io.returm.front.management.charge.mapper.InstalledDeviceMapper;
import io.returm.front.management.charge.mapper.RewardDeviceMapper;
import io.returm.front.management.charge.mapper.RewardMapper;
import io.returm.front.management.charge.mapper.RewardTemporarilyMapper;
import io.returm.front.management.common.mapper.AdminWalletMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ChargeCallbackService {

    private Logger logger = LoggerFactory.getLogger(ChargeCallbackService.class);

    @Autowired
    private RewardMapper rewardMapper;

    @Autowired
    private InstalledDeviceMapper installedDeviceMapper;

    @Autowired
    private AdminWalletMapper adminWalletMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RewardTemporarilyMapper rewardTemporarilyMapper;

    @Autowired
    private RewardDeviceMapper rewardDeviceMapper;

    public void insert(RewardEntity entity) throws DuplicateRewardException {

        RewardEntity validEntity = new RewardEntity();
        validEntity.setInsertId(entity.getWalletAddrFrom());
        validEntity.setRemoteSeq(entity.getRemoteSeq());
        validEntity.setType(entity.getType());

        validEntity = rewardMapper.get(validEntity);
        if(validEntity != null) {
            throw new DuplicateRewardException();
        }

        rewardMapper.insert(entity);
    }

    public RewardEntity getReward(RewardEntity entity) {
        return rewardMapper.get(entity);
    }

    public InstalledDeviceEntity getInstallDevice(InstalledDeviceEntity entity) {
        return installedDeviceMapper.get(entity);
    }

    public void insertInstallDevice(InstalledDeviceEntity entity) {
        installedDeviceMapper.insert(entity);
    }

    public void updateResult(RewardEntity entity) {
        rewardMapper.updateResult(entity);
    }

    public RewardTemporarilyEntity getRewardTransaction(RewardTemporarilyEntity entity) {
        return rewardTemporarilyMapper.get(entity);
    }

    public void insertRewardTransaction(RewardTemporarilyEntity entity) {
        rewardTemporarilyMapper.insert(entity);
    }

    public void updateRewardTransaction(RewardTemporarilyEntity entity) {
        rewardTemporarilyMapper.update(entity);
    }

    public RewardDeviceEntity getRewardDevice(RewardDeviceEntity entity) {
        return rewardDeviceMapper.get(entity);
    }

    public int getRewardDeviceCount(RewardDeviceEntity entity) {
        return rewardDeviceMapper.count(entity);
    }

    public void insertRewardDevice(RewardDeviceEntity entity) {
        rewardDeviceMapper.insert(entity);
    }
}
