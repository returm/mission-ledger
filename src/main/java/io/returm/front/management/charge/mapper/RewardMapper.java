package io.returm.front.management.charge.mapper;

import io.returm.front.management.charge.entity.RewardEntity;

import java.util.List;

public interface RewardMapper {

    public RewardEntity get(RewardEntity entity);

    public List<RewardEntity> getList(RewardEntity entity);

    public int count(RewardEntity entity);

    public void insert(RewardEntity entity);

    public void updateResult(RewardEntity entity);
}
