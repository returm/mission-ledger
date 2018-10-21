package io.returm.front.management.common.controller;

import io.returm.front.management.common.entity.AppFileReqEntity;
import io.returm.front.management.common.entity.AppFileResEntity;
import io.returm.front.management.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/common")
public class CommonController {

    private Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 앱 버전 체크
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping("checkUpdate")
    @ResponseBody
    public AppFileResEntity checkUpdate(@ModelAttribute AppFileReqEntity entity,
                                        HttpServletRequest request) {

        return commonService.processCheckUpdate(entity);
    }
}
