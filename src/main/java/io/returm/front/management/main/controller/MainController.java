package io.returm.front.management.main.controller;
import com.google.gson.Gson;
import io.returm.front.annotation.CmCode;
import io.returm.front.annotation.EntryPoint;
import io.returm.front.common.util.AESUtil;
import io.returm.front.common.web.entity.BaseReqEntity;
import io.returm.front.common.web.entity.ReqParamEntity;
import io.returm.front.constants.TilesSuffix;
import io.returm.front.management.mission.service.MissionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main")
public class MainController {

    /** Logger */
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private final String PREFIX = "main/";

    @Autowired
    private MissionService missionService;

    @CmCode({"RT001"})
    @EntryPoint
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main(@ModelAttribute BaseReqEntity entity, Model model, HttpServletRequest request) {

        String savedWalletAddr = (String)request.getAttribute("savedWalletAddr");

        if(StringUtils.isNotBlank(savedWalletAddr)) {
            model.addAttribute("message", "지갑주소[" + savedWalletAddr + "]의 접속이력이 있어 기존 지갑주소로 연결합니다. 이력은 하루동안 유지됩니다.");

            Gson gson = new Gson();

            ReqParamEntity reqParamEntity = new ReqParamEntity();
            reqParamEntity.setAddress(savedWalletAddr);
            reqParamEntity.setAccesskey(entity.getParameter().getAccesskey());

            try {

                String jsonString = gson.toJson(reqParamEntity);
                String p = AESUtil.encrypt(jsonString);

                entity.setP(p);
                entity.getParameter().setAddress(savedWalletAddr);

            }catch(Exception e) { }
        }

        model.addAttribute("address", entity.getParameter().getAddress());
        model.addAttribute("parameter", entity.getP());
        model.addAttribute("missionComm", missionService.getMissionCommon());
        model.addAttribute("newMissionList", missionService.getNewMissionList());
        model.addAttribute("recomMissionList", missionService.getRecommendMissionList());

        return PREFIX + "main" + TilesSuffix.DEFAULT;
    }

}
