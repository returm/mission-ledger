package io.returm.front.management.charge.controller;

import com.google.gson.Gson;

import io.returm.framework.util.JSONUtil;
import io.returm.front.common.exception.DuplicateRewardException;
import io.returm.front.common.util.AESUtil;
import io.returm.front.common.util.Config;
import io.returm.front.common.util.HmacUtils;
import io.returm.front.common.web.entity.ReqParamEntity;
import io.returm.front.constants.TilesSuffix;
import io.returm.front.management.charge.entity.*;
import io.returm.front.management.charge.service.ChargeCallbackService;
import io.returm.front.management.common.service.CommonService;
import io.returm.front.management.constants.RT001;
import io.returm.front.management.constants.RT002;
import io.returm.front.management.mission.entity.MissionEntity;
import io.returm.front.management.mission.service.MissionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
@RequestMapping("/rest/charge")
public class ChargeCallbackController {

    private Logger logger = LoggerFactory.getLogger(ChargeCallbackController.class);

    @Autowired
    private ChargeCallbackService service;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MissionService missionService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "result", method = RequestMethod.GET)
    public ResponseEntity result(@ModelAttribute ResultReqEntity entity, HttpServletRequest request) {

        try {

            Gson gson = new Gson();

            String decryptParameter = AESUtil.decrypt(entity.getP());
            WalletResultReqEntity resultReqEntity = gson.fromJson(decryptParameter, WalletResultReqEntity.class);

            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setRewardSeq(resultReqEntity.getToken());
            rewardEntity.setResult(resultReqEntity.getStatus());

            service.updateResult(rewardEntity);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "transaction", method = RequestMethod.GET)
    public String temp(@ModelAttribute RewardTemporarilyReqEntity entity,
                       Model model,
                       HttpServletRequest request) {

        try {

            String decryptParameter = AESUtil.decrypt(entity.getP());

            Gson gson = new Gson();
            ReqParamEntity paramEntity = gson.fromJson(decryptParameter, ReqParamEntity.class);

            MissionEntity missionEntity = new MissionEntity();
            missionEntity.setMissionSeq(entity.getMissionSeq());
            missionEntity = missionService.getMission(missionEntity);

            if("130".equals(missionEntity.getMissionTypeCd()) || "150".equals(missionEntity.getMissionTypeCd()) ||
                    "140".equals(missionEntity.getMissionTypeCd()) || "120".equals(missionEntity.getMissionTypeCd())) {

                RewardDeviceEntity rewardDeviceEntity = new RewardDeviceEntity();
                rewardDeviceEntity.setMissionSeq(missionEntity.getMissionSeq());
                rewardDeviceEntity.setWalletAddr(paramEntity.getAddress());
                rewardDeviceEntity.setTermDays("4");

                int validRewardDeviceEntity = service.getRewardDeviceCount(rewardDeviceEntity);

                if(validRewardDeviceEntity < 1) {

                    RewardEntity rewardEntity = new RewardEntity();
                    rewardEntity.setType(missionEntity.getMissionTypeCd());
                    rewardEntity.setPoint(missionEntity.getRewardPoint());
                    rewardEntity.setContents(missionEntity.getTitle());
                    rewardEntity.setWalletAddrTo(paramEntity.getAddress());
                    rewardEntity.setMissionSeq(missionEntity.getMissionSeq());
                    rewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());

                    service.insertRewardDevice(rewardDeviceEntity);
                    service.insert(rewardEntity);
                    insertRewardToWallet(rewardEntity);

                    model.addAttribute("linkUrl", missionEntity.getLinkUrl());
                    model.addAttribute("rmPoint", missionEntity.getRewardPoint());
                    return "mission/" + "processSuccess" + TilesSuffix.EMPTY;
                }else {
                    model.addAttribute("linkUrl", missionEntity.getLinkUrl());
                    return "mission/" + "processFailure" + TilesSuffix.EMPTY;
                }
            }else if("100".equals(missionEntity.getMissionTypeCd()) || "160".equals(missionEntity.getMissionTypeCd()) ||
                    "170".equals(missionEntity.getMissionTypeCd()) || "180".equals(missionEntity.getMissionTypeCd()) || "900".equals(missionEntity.getMissionTypeCd())) {

                RewardDeviceEntity rewardDeviceEntity = new RewardDeviceEntity();
                rewardDeviceEntity.setMissionSeq(missionEntity.getMissionSeq());
                rewardDeviceEntity.setWalletAddr(paramEntity.getAddress());
                rewardDeviceEntity.setTermDays("2");

                int validRewardDeviceEntity = service.getRewardDeviceCount(rewardDeviceEntity);

                if(validRewardDeviceEntity < 1) {

                    RewardTemporarilyEntity rewardTemporarilyEntity = new RewardTemporarilyEntity();
                    rewardTemporarilyEntity.setTempSeq(ObjectId.get().toString());
                    rewardTemporarilyEntity.setMissionSeq(missionEntity.getMissionSeq());
                    rewardTemporarilyEntity.setSendYn("N");
                    rewardTemporarilyEntity.setWalletAddrTo(paramEntity.getAddress());

                    service.insertRewardTransaction(rewardTemporarilyEntity);

                    String linkUrl = "";

                    if(missionEntity.getLinkUrl().indexOf("?") > -1) {
                        linkUrl = missionEntity.getLinkUrl() + "&Tid="+ rewardTemporarilyEntity.getTempSeq() + "&point=" + missionEntity.getRewardPoint();
                    }else {
                        linkUrl = missionEntity.getLinkUrl() + "?Tid="+rewardTemporarilyEntity.getTempSeq()  + "&point=" + missionEntity.getRewardPoint();
                    }

                    model.addAttribute("linkUrl", linkUrl);
                    model.addAttribute("rmPoint", missionEntity.getRewardPoint());
                    return "mission/" + "processSuccess" + TilesSuffix.EMPTY;
                }else {
                    model.addAttribute("linkUrl", missionEntity.getLinkUrl() + "?Tid=");
                    return "mission/" + "processFailure" + TilesSuffix.EMPTY;
                }

            }else if("110".equals(missionEntity.getMissionTypeCd())) {

                RewardTemporarilyEntity rewardTemporarilyEntity = new RewardTemporarilyEntity();
                rewardTemporarilyEntity.setTempSeq(ObjectId.get().toString());
                rewardTemporarilyEntity.setMissionSeq(missionEntity.getMissionSeq());
                rewardTemporarilyEntity.setSendYn("N");
                rewardTemporarilyEntity.setWalletAddrTo(paramEntity.getAddress());

                service.insertRewardTransaction(rewardTemporarilyEntity);

                if(missionEntity.getLinkUrl().indexOf("?") > -1) {
                    return "redirect:"+missionEntity.getLinkUrl() + "&Tid="+rewardTemporarilyEntity.getTempSeq()  + "&point=" + missionEntity.getRewardPoint();
                }else {
                    return "redirect:"+missionEntity.getLinkUrl() + "?Tid="+rewardTemporarilyEntity.getTempSeq()  + "&point=" + missionEntity.getRewardPoint();
                }
            }



        }catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @RequestMapping(value = "callback/igaworks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public IgaworksCallbackResEntity igaworks(@ModelAttribute IgaworksCallbackReqEntity entity) {

        IgaworksCallbackResEntity resEntity = new IgaworksCallbackResEntity();
        resEntity.setResult(true);
        resEntity.setResultCode(1);
        resEntity.setResultMsg("success");

        // signed_value 체크
        String igaworksHashKey = Config.getCommon().getString("IGAWORKS.HASH");

        String _signedValue = String.format("%s%s%s%s", entity.getUsn(), entity.getReward_key(), entity.getQuantity(), entity.getCampaign_key());
        String signedValue = HmacUtils.hmacDigest(_signedValue, igaworksHashKey, "HmacMD5");

        if(!entity.getSigned_value().equals(signedValue)) {
            resEntity.setResultMsg("invalid signed value");
            resEntity.setResultCode(1100);
            resEntity.setResult(false);
            return resEntity;
        }

        try {
            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setType("igaworks");
            rewardEntity.setRemoteSeq(entity.getReward_key());
            rewardEntity.setPoint(entity.getQuantity());
            rewardEntity.setContents("igawors 리워드 적립");
            rewardEntity.setWalletAddrTo(entity.getUsn());
            rewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());

            service.insert(rewardEntity);

            insertRewardToWallet(rewardEntity);

        }catch(DuplicateRewardException e) {
            e.printStackTrace();
            resEntity.setResultMsg("duplicate transaction");
            resEntity.setResultCode(3100);
            resEntity.setResult(false);
        }catch(Exception e) {
            e.printStackTrace();
            resEntity.setResultMsg("unknown error");
            resEntity.setResultCode(4000);
            resEntity.setResult(false);
        }

        return resEntity;
    }

    @RequestMapping(value = "callback/appang", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity appang(@ModelAttribute AppangCallbackReqEntity entity) {

        try {

            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setType("appang");
            rewardEntity.setRemoteSeq(entity.getSeqId());
            rewardEntity.setPoint(entity.getReward());
            rewardEntity.setContents(String.format("appang 리워드 적립 : %s", entity.getAdName()));
            rewardEntity.setWalletAddrTo(entity.getUserDate());
            rewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());

            service.insert(rewardEntity);

            insertRewardToWallet(rewardEntity);

        }catch(DuplicateRewardException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "callback/tnkfactory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity tnkfactory(@ModelAttribute TnkFactoryCallbackReqEntity entity) {

        String appKey = Config.getCommon().getString("TNKFACTORY.HASH");
        String verifyCode = DigestUtils.md5Hex(appKey + entity.getMd_user_nm() + entity.getSeq_id());
        if(StringUtils.isEmpty(entity.getMd_chk()) || !entity.getMd_chk().equals(verifyCode)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {

            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setType("tnkfactory");
            rewardEntity.setRemoteSeq(entity.getSeq_id());
            rewardEntity.setPoint(entity.getPay_pnt());
            rewardEntity.setContents("tnkfactory 리워드 적립");
            rewardEntity.setWalletAddrTo(entity.getMd_user_nm());
            rewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());

            service.insert(rewardEntity);

            insertRewardToWallet(rewardEntity);

        }catch(DuplicateRewardException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "callback/appall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity appall(@ModelAttribute AppallCallbackReqEntity entity) {

        try {

            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setType("appall");
            rewardEntity.setRemoteSeq(entity.getSeq_id());
            rewardEntity.setPoint(entity.getPoint());
            rewardEntity.setContents(String.format("appall 리워드 적립 : %s", entity.getAd_name()));
            rewardEntity.setWalletAddrTo(entity.getUser_id());
            rewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());

            service.insert(rewardEntity);

            insertRewardToWallet(rewardEntity);

        }catch(DuplicateRewardException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "callback/install", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity install(@RequestBody InstallRewardReqEntity entity, HttpServletRequest request) {

        try {

            String decryptParameter = AESUtil.decrypt(entity.getP());

            Gson gson = new Gson();
            ReqParamEntity paramEntity = gson.fromJson(decryptParameter, ReqParamEntity.class);

            String address = paramEntity.getAddress();
            String accessKey = paramEntity.getAccesskey();

            InstalledDeviceEntity installedDeviceEntity = new InstalledDeviceEntity();
            installedDeviceEntity.setDeviceId(entity.getDeviceId());
            installedDeviceEntity.setGoogleId(entity.getGoogleId());
            installedDeviceEntity = service.getInstallDevice(installedDeviceEntity);

            if(installedDeviceEntity != null) {
                throw new Exception();
            }

            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setWalletAddrTo(address);
            rewardEntity.setType(RT001.RETURM_MISSION.toString());
            rewardEntity.setSubType(RT002.RETURM_INSTALL.toString());
            rewardEntity = service.getReward(rewardEntity);

            if(rewardEntity != null) {
                throw new Exception();
            }

            MissionEntity installMission = new MissionEntity();
            installMission.setMissionTypeCd(RT001.RETURM_MISSION.toString());
            installMission.setMissionSubTypeCd(RT002.RETURM_INSTALL.toString());
            installMission = missionService.getMission(installMission);

            if(installMission == null) {
                throw new Exception();
            }

            RewardEntity insertRewardEntity = new RewardEntity();
            insertRewardEntity.setType(RT001.RETURM_MISSION.toString());
            insertRewardEntity.setSubType(RT002.RETURM_INSTALL.toString());
            insertRewardEntity.setWalletAddrTo(address);
            insertRewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());
            insertRewardEntity.setPoint(installMission.getRewardPoint());
            insertRewardEntity.setContents(installMission.getTitle());

            service.insert(insertRewardEntity);


            InstalledDeviceEntity insertDeviceEntity = new InstalledDeviceEntity();
            insertDeviceEntity.setRewardSeq(insertRewardEntity.getRewardSeq());
            insertDeviceEntity.setDeviceId(entity.getDeviceId());
            insertDeviceEntity.setGoogleId(entity.getGoogleId());
            insertDeviceEntity.setInsertId("SYSTEM");

            service.insertInstallDevice(insertDeviceEntity);

            insertRewardToWallet(insertRewardEntity);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);

    }

    @RequestMapping(value = "callback/appmission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity appmission(@RequestBody MissionCallbackReqEntity entity, HttpServletRequest request) {

        try {
            RewardTemporarilyEntity rewardTemporarilyEntity = new RewardTemporarilyEntity();
            rewardTemporarilyEntity.setTempSeq(entity.getP());
            rewardTemporarilyEntity = service.getRewardTransaction(rewardTemporarilyEntity);

            if(rewardTemporarilyEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            MissionEntity missionEntity = new MissionEntity();
            missionEntity.setMissionSeq(rewardTemporarilyEntity.getMissionSeq());
            missionEntity = missionService.getMission(missionEntity);

            if(missionEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            RewardDeviceEntity check1 = new RewardDeviceEntity();
            check1.setDeviceId(entity.getDeviceId());
            check1.setMissionSeq(missionEntity.getMissionSeq());
            check1.setTermDays("2");
            int check1Count = service.getRewardDeviceCount(check1);

            if(check1Count > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            RewardDeviceEntity check2 = new RewardDeviceEntity();
            check2.setMissionSeq(missionEntity.getMissionSeq());
            check2.setWalletAddr(rewardTemporarilyEntity.getWalletAddrTo());
            check2.setTermDays("2");
            int check2Count = service.getRewardDeviceCount(check2);

            if(check2Count > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            RewardDeviceEntity insertRewardDeviceEntity = new RewardDeviceEntity();
            insertRewardDeviceEntity.setDeviceId(entity.getDeviceId());
            insertRewardDeviceEntity.setWalletAddr(rewardTemporarilyEntity.getWalletAddrTo());
            insertRewardDeviceEntity.setMissionSeq(missionEntity.getMissionSeq());
            service.insertRewardDevice(insertRewardDeviceEntity);

            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setType(missionEntity.getMissionTypeCd());
            rewardEntity.setPoint(missionEntity.getRewardPoint());
            rewardEntity.setContents(missionEntity.getTitle());
            rewardEntity.setMissionSeq(missionEntity.getMissionSeq());
            rewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());
            rewardEntity.setWalletAddrTo(rewardTemporarilyEntity.getWalletAddrTo());

            service.insert(rewardEntity);

            insertRewardToWallet(rewardEntity);

            rewardTemporarilyEntity.setSendYn("Y");
            service.updateRewardTransaction(rewardTemporarilyEntity);

        }catch (DuplicateRewardException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "callback/mission", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String mission(@ModelAttribute MissionCallbackReqEntity entity, HttpServletRequest request) {

        MissionCallbackResEntity resEntity = new MissionCallbackResEntity();

        try {

            RewardTemporarilyEntity rewardTemporarilyEntity = new RewardTemporarilyEntity();
            rewardTemporarilyEntity.setTempSeq(entity.getP());
            rewardTemporarilyEntity = service.getRewardTransaction(rewardTemporarilyEntity);

            if(rewardTemporarilyEntity == null) {
                throw new Exception();
            }

            MissionEntity missionEntity = new MissionEntity();
            missionEntity.setMissionSeq(rewardTemporarilyEntity.getMissionSeq());
            missionEntity = missionService.getMission(missionEntity);

            if(missionEntity == null) {
                throw new Exception();
            }

            RewardEntity rewardEntity = new RewardEntity();
            rewardEntity.setType(missionEntity.getMissionTypeCd());
            rewardEntity.setPoint(missionEntity.getRewardPoint());
            rewardEntity.setContents(missionEntity.getTitle());
            rewardEntity.setMissionSeq(missionEntity.getMissionSeq());
            rewardEntity.setWalletAddrFrom(commonService.getAdminWalletAddress().getWalletAddr());
            rewardEntity.setWalletAddrTo(rewardTemporarilyEntity.getWalletAddrTo());

            service.insert(rewardEntity);

            insertRewardToWallet(rewardEntity);

            rewardTemporarilyEntity.setSendYn("Y");
            service.updateRewardTransaction(rewardTemporarilyEntity);

            resEntity.setResult("success");

        }catch(Exception e) {
            resEntity.setResult("failure");
        }finally {

            String resObj = JSONUtil.parseJson(resEntity);

            String callback = request.getParameter("callback");
            if(StringUtils.isNotBlank(callback)) {
                StringBuffer respBody = new StringBuffer();
                respBody.append(callback).append("(").append(resObj).append(")");
                return respBody.toString();
            }
        }

        return JSONUtil.parseJson(resEntity);
    }


    private void insertRewardToWallet(RewardEntity rewardEntity) {

        try {

            String accessKey = Config.getCommon().getString("ACCESS.KEY");

            InsertToWalletReqEntity reqEntity = new InsertToWalletReqEntity();
            reqEntity.setAccessKey(accessKey);
            reqEntity.setAddress(rewardEntity.getWalletAddrTo());
            reqEntity.setToken(rewardEntity.getRewardSeq());
            reqEntity.setMemo(rewardEntity.getContents());
            reqEntity.setAmount(rewardEntity.getPoint());
            reqEntity.setFromAddress(rewardEntity.getWalletAddrFrom());

            Gson gson = new Gson();
            String jsonString = gson.toJson(reqEntity);
            String enString = AESUtil.encrypt(jsonString);

            logger.debug("jsonString : {}", jsonString);
            logger.debug("enString : {}", enString);

            HttpHeaders headers = new HttpHeaders();

            URI uri = UriComponentsBuilder.newInstance().scheme("https").host("returm.io").path("/api/mission")
                    .queryParam("p", enString)
                    .build().encode().toUri();

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<InsertToWalletResEntity> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    InsertToWalletResEntity.class);

            logger.debug("Request To Wallet : {}", jsonString);
            logger.debug("Request To Wallet : {}", response.getBody().getRes().getCode());
            logger.debug("Request To Wallet : {}", response.getBody().getRes().getMsg());

            if(200 == response.getBody().getRes().getCode()) {
                rewardEntity.setResult("reqsuccess");
            }else {
                rewardEntity.setResult("reqfailure");
            }

        }catch(Exception e) {
            e.printStackTrace();
            rewardEntity.setResult("reqfailure");
        }

        service.updateResult(rewardEntity);
    }
}
