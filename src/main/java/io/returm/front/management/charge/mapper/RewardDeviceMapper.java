package io.returm.front.management.charge.mapper;

import io.returm.front.management.charge.entity.RewardDeviceEntity;

import java.util.List;

public interface RewardDeviceMapper {

    public RewardDeviceEntity get(RewardDeviceEntity entity);

    public void insert(RewardDeviceEntity entity);

    public int count(RewardDeviceEntity entity);

}
