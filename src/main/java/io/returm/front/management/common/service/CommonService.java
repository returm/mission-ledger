package io.returm.front.management.common.service;

import io.returm.front.management.common.entity.AdminWalletEntity;
import io.returm.front.management.common.entity.AppFileReqEntity;
import io.returm.front.management.common.entity.AppFileResEntity;
import io.returm.front.management.common.mapper.AdminWalletMapper;
import io.returm.front.management.common.mapper.AppFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    private Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private AppFileMapper appFileMapper;

    @Autowired
    private AdminWalletMapper adminWalletMapper;

    public AppFileResEntity processCheckUpdate(AppFileReqEntity entity) {
        return appFileMapper.checkUpdate(entity);
    }

    public AdminWalletEntity getAdminWalletAddress() {
        return adminWalletMapper.getAdminWalletAddress();
    }

}
