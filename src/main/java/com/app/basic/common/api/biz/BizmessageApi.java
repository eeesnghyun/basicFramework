package com.app.basic.common.api.biz;

import com.app.basic.common.entity.MsgEntity;
import com.app.basic.common.entity.StatusEnum;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("biz")
public class BizmessageApi {

	private static final String ALIMTALK_API_URL = "https://api-alimtalk.cloud.toast.com/alimtalk/v2.2/appkeys";

    @Value("${spring.biz.senderKey}")
	private String BIZ_SENDER_KEY;
	@Value("${spring.biz.appKey}")
	private String BIZ_APP_KEY;
	@Value("${spring.biz.secretKey}")
	private String BIZ_SECRET_KEY;

    @PostMapping(value="/send")
    public MsgEntity send(@RequestBody BizDTO bizDTO) throws Exception {
 		JSONArray recipientList = new JSONArray();
		recipientList.add(getTemplateBody(bizDTO.getMobile()));

		Map<String, Object> apiParamMap = callRestApi(bizDTO.getTemplateCode(), recipientList);

		return MsgEntity.builder()
                    .message(StatusEnum.OK)
                    .build();
    }

    public JSONObject getTemplateBody(String mobile) {
		JSONObject recipientJson = new JSONObject();
		recipientJson.put("recipientNo", mobile);
		recipientJson.put("content", "테스트 알림톡입니다.");

		JSONArray buttonList = new JSONArray();
		JSONObject buttonJson = new JSONObject();

		buttonJson.put("ordering", 1);
        buttonJson.put("type"    , "WL");
        buttonJson.put("name"    , "테스트 이동");
        buttonJson.put("linkMo"  , "https://codedream.co.kr");
        buttonJson.put("linkPc"  , "https://codedream.co.kr");
        buttonList.add(buttonJson);

		recipientJson.put("buttons", buttonList);

		return recipientJson;
	}

	private Map<String, Object> callRestApi(
		String templateCode,
		JSONArray recipientList
	) {
		String bizApiUrl = ALIMTALK_API_URL + "/" + BIZ_APP_KEY + "/raw-messages";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-Secret-Key", BIZ_SECRET_KEY);

		JSONObject paramJson = new JSONObject();
		paramJson.put("senderKey", BIZ_SENDER_KEY);
		paramJson.put("templateCode", templateCode);
		paramJson.put("recipientList", recipientList);

		HttpEntity<String> requestEntity = new HttpEntity<>(paramJson.toString(), headers);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(bizApiUrl, requestEntity, String.class);
		String responseBody = responseEntity.getBody();

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", responseEntity.getStatusCodeValue());
		resultMap.put("message", responseEntity.getStatusCode().getReasonPhrase());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			JsonParser parser = new JsonParser();
			JsonObject responseData = parser.parse(responseBody).getAsJsonObject();
			resultMap.put("responseData", responseData);
		}

		return resultMap;
	}


}
