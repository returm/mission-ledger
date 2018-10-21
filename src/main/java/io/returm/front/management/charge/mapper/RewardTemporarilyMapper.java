package io.returm.front.management.charge.mapper;

import io.returm.front.management.charge.entity.RewardTemporarilyEntity;

public interface RewardTemporarilyMapper {

    public RewardTemporarilyEntity get(RewardTemporarilyEntity entity);

    public void insert(RewardTemporarilyEntity entity);

    public void update(RewardTemporarilyEntity entity);
}
