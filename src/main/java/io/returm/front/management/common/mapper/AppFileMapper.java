package io.returm.front.management.common.mapper;

import io.returm.front.management.common.entity.AppFileReqEntity;
import io.returm.front.management.common.entity.AppFileResEntity;

public interface AppFileMapper {

    public AppFileResEntity checkUpdate(AppFileReqEntity entity);

}
