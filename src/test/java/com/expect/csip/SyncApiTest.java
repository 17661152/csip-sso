package com.expect.csip;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.expect.common.enums.PublicEnum;
import com.expect.csip.iam.domain.Service;
import com.expect.csip.iam.service.ServiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Starter.class)
public class SyncApiTest {

    @Autowired
    private ServiceService serviceService;

    @Test
    public void sync() {
        String apiResult = HttpUtil.get("http://localhost:8100/v2/api-docs");
        Map<String, Object> jsonMap = JSONUtil.toBean(apiResult, HashMap.class);
        Map<String, JSONObject> pathMap = (Map<String, JSONObject>) jsonMap.get("paths");
        pathMap.forEach((key, value) -> {
            JSONObject methodMap = (JSONObject) ((Map<String, Object>)value).get("post");
            if (null == methodMap || methodMap.isEmpty()) {
                methodMap = (JSONObject)((Map<String, Object>)value).get("get");
            }
            String summary = (String)methodMap.get("summary");
            String tag = (String) ((JSONArray) methodMap.get("tags")).get(0);

            JSONObject tagMap = getTag(tag, (JSONArray)jsonMap.get("tags"));
            String controllerName = tagMap.get("description").toString();
            controllerName = controllerName.replaceAll(" ", "");

            Service service = serviceService.getServiceByAccessAddress(key);
            if (null == service) {
                service = new Service();
                service.setName(summary);
                service.setServiceName(controllerName);
                service.setAccessAddress(key);
                service.setDescr(summary);
                service.setCreatePerson("1085713213909594114");
                service.setLastUpdatePerson("1085713213909594114");
                if (key.indexOf("list") == -1 && key.indexOf("get") == -1) {
                    service.setIsRecordAccessLog(PublicEnum.YES.getStatus());
                } else {
                    service.setIsRecordAccessLog(PublicEnum.NO.getStatus());
                }
                serviceService.saveService(service);
            }
        });
    }

    private JSONObject getTag(String tag, JSONArray tags) {
        Iterator iterator =  tags.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            if (tag.equals(jsonObject.get("name").toString())) {
                return jsonObject;
            }
        }
        return null;
    }
}
