package io.returm.front.management.mission.controller;

import com.google.gson.Gson;
import io.returm.front.annotation.CmCode;
import io.returm.front.annotation.EntryPoint;
import io.returm.front.common.mobile.entity.ListResEntity;
import io.returm.front.common.util.AESUtil;
import io.returm.front.common.util.MessageManage;
import io.returm.front.common.web.entity.BaseReqEntity;
import io.returm.front.common.web.entity.PagingValue;
import io.returm.front.common.web.entity.ReqParamEntity;
import io.returm.front.constants.TilesSuffix;
import io.returm.front.management.charge.entity.RewardEntity;
import io.returm.front.management.constants.RT001;
import io.returm.front.management.mission.entity.MissionEntity;
import io.returm.front.management.mission.entity.MissionResEntity;
import io.returm.front.management.mission.service.MissionService;
import io.returm.front.management.system.entity.CodeEntity;
import io.returm.front.management.system.service.CodeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mission")
public class MissionController {

    private Logger logger = LoggerFactory.getLogger(MissionController.class);

    private final String PREFIX = "mission/";

    @Autowired
    private MissionService missionService;

    @Autowired
    private CodeService codeService;

    @CmCode({"RT001"})
    @EntryPoint
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String missionView(@ModelAttribute BaseReqEntity entity,
                              Model model, HttpServletRequest request) {

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

        MissionEntity returmMissionEntity = new MissionEntity();
        returmMissionEntity.setMissionTypeCd(RT001.RETURM_MISSION.toString());

        model.addAttribute("address", entity.getParameter().getAddress());
        model.addAttribute("parameter", entity.getP());
        model.addAttribute("missionComm", missionService.getMissionCommon());
        model.addAttribute("returmMissionList", missionService.getMissionList(returmMissionEntity));

        return PREFIX + "list" + TilesSuffix.DEFAULT;
    }

    @RequestMapping(value = "list/{missionTypeCd}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ListResEntity<MissionEntity> list(
            @PathVariable(value = "missionTypeCd") String missionTypeCd,
            @ModelAttribute MissionEntity entity,
                              Model model,
                              HttpServletRequest request) {

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null) {
            model.addAttribute("message", flashMap.get("message"));
        }

        entity.setMissionTypeCd(missionTypeCd);
        List<MissionEntity> resultList = missionService.getMissionList(entity);
        PagingValue page = entity.getPaging();

        ListResEntity<MissionEntity> resEntity = new ListResEntity<>();
        resEntity.setList(resultList);
        resEntity.setPage(page);

        return resEntity;
    }

    @EntryPoint
    @RequestMapping(value = "list/{missionTypeCd}", method = RequestMethod.GET)
    public String missionTypeView(@ModelAttribute MissionEntity entity,
                                  Model model, HttpServletRequest request) {

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

        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCdgrpCd("RT001");
        codeEntity.setCodeCd(entity.getMissionTypeCd());
        codeEntity = codeService.get(codeEntity);

        model.addAttribute("address", entity.getParameter().getAddress());
        model.addAttribute("parameter", entity.getP());
        model.addAttribute("missionTypeCdNm", codeEntity.getCodeNm());
        model.addAttribute("missionTypeCd", entity.getMissionTypeCd());
        model.addAttribute("missionComm", missionService.getMissionCommon());

        String viewName = "type";
        if(entity.getMissionTypeCd().equals(RT001.AD_MISSION.toString())) {
            viewName = "mission_ad";
        }

        return PREFIX + viewName + TilesSuffix.DEFAULT;
    }

    @RequestMapping(value = "detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MissionResEntity detail(@ModelAttribute MissionEntity entity,
                                   HttpServletRequest request) {

        MissionResEntity resEntity = new MissionResEntity();
        resEntity.setMission(missionService.getMission(entity));

        return resEntity;
    }

    /**
     * 미션 정보 등록 폼
     * @param entity
     * @param model
     * @param request
     * @return
     */
    @CmCode({"RT001"})
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String addForm(@ModelAttribute MissionEntity entity,
                          Model model,
                          HttpServletRequest request) {

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null) {
            model.addAttribute("message", flashMap.get("message"));
        }

        model.addAttribute("address", entity.getParameter().getAddress());
        model.addAttribute("parameter", entity.getP());

        return PREFIX + "form" + TilesSuffix.DEFAULT;
    }

    /**
     * 미션 정보 등록
     * @param entity
     * @param result
     * @param model
     * @param attrs
     * @param request
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.POST)
    public String add(@ModelAttribute MissionEntity entity,
                          BindingResult result,
                          Model model,
                          RedirectAttributes attrs,
                          HttpServletRequest request) {

        // Validator Error
        if(result.hasErrors()) {

        }

        // 등록
        missionService.insertMission(entity);

        // Message
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.put("message", MessageManage.getMessage("save.success"));

        return "redirect:list";
    }

    /**
     * 나의 미션 정보 화면
     * @param entity
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "mymission", method = RequestMethod.GET)
    public String mypage(@ModelAttribute RewardEntity entity,
                         Model model,
                         HttpServletRequest request) {

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null) {
            model.addAttribute("message", flashMap.get("message"));
        }

        model.addAttribute("address", entity.getParameter().getAddress());
        model.addAttribute("parameter", entity.getP());

        return PREFIX + "mymission" + TilesSuffix.DEFAULT;
    }

    @RequestMapping(value = "mymission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ListResEntity<RewardEntity> myMissionList(@ModelAttribute RewardEntity entity,
                                                      HttpServletRequest request) {

        ListResEntity<RewardEntity> resEntity = new ListResEntity<>();

        List<RewardEntity> resultList = missionService.getMyRewardList(entity);
        PagingValue page = entity.getPaging();

        resEntity.setList(resultList);
        resEntity.setPage(page);

        return resEntity;
    }

    @RequestMapping(value = "process", method = RequestMethod.GET)
    public String process(Model model, HttpServletRequest request) {

        return PREFIX + "processSuccess" + TilesSuffix.EMPTY;
    }

//    @RequestMapping(value = "process/success", method = RequestMethod.GET)
//    public String processSuccess(Model model, HttpServletRequest request) {
//
//
//        return PREFIX + "processSuccess" + TilesSuffix.DEFAULT;
//    }
//
//    @RequestMapping(value = "process/failure", method = RequestMethod.GET)
//    public String processFailure(Model model, HttpServletRequest request) {
//
//        return PREFIX + "processFailure" + TilesSuffix.DEFAULT;
//    }

}
