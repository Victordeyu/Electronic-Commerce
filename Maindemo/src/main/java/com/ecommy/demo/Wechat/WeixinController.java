package com.ecommy.demo.Wechat;

import com.ecommy.demo.Common.Enums.ResultVOEnum;
import com.ecommy.demo.Common.VO.ResultVO;
import com.ecommy.demo.config.PayConfig;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
@Api
public class WeixinController {
    @Autowired
    private PayConfig payConfig;

    @ApiOperation("weixinlogin")
    @GetMapping("/auth")
    public ResultVO auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + payConfig.getAppId() + "&secret=" + payConfig.getAppSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        StringMap stringMap = Json.decode(response);
        String openid = stringMap.get("openid").toString();

        if (StringUtils.isNullOrEmpty(openid)) {
            //todo
        }
        log.info("response={}", response);
        return ResultVOEnum.success(openid);
    }
}
