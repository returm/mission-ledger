package io.returm.front.management.charge.mapper;

import io.returm.front.management.charge.entity.InstalledDeviceEntity;

public interface InstalledDeviceMapper {

    public InstalledDeviceEntity get(InstalledDeviceEntity entity);

    public void insert(InstalledDeviceEntity entity);
}
