package edu.fudan.selab.controller;

import edu.fudan.selab.entity.Coffee;
import edu.fudan.selab.ontology.HttpRequestor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.fudan.selab.globle.Constants.*;

@RestController
public class GetCoffeeLink {
    @RequestMapping(value = "/getcoffeelink", method = RequestMethod.GET)
    public String getLink(HttpServletRequest request) throws Exception{
        String uri =  "http://" + request.getHeader("Host")  + request.getRequestURI(); // "http://127.0.0.1:8002/s/getcoffeelink";//
        String link = "";
        String regex = "(?<=://)[a-zA-Z\\.0-9]+(:[0-9]+)?(?=\\/)";//"(?<=\\\\/)[a-zA-Z\\\\.:0-9]+(?=\\\\/)"; // 匹配两个斜杠之间的字符串 /.../

        uri = uri.replaceAll(regex, "");
        uri = uri.replaceAll("http(s)?://", "");
        uri = uri.replaceAll("/getcoffeelink", "");
        String[] split_url = uri.split("\\/");
        List<String> list = Arrays.asList(split_url);

        if(split_url.length > 0){
            link = local_url + String.join("/", list) +"/coffee";
        }else{
            link = local_url + "/" + war_name +"/coffee";
        }
        link = local_url + "/" + war_name +"/coffee";

        String taskId = request.getParameter("processIdFromApp");
        workflow_instance_id = request.getParameter("workflow_instance_id");
        instance_id = request.getParameter("instance_id");
        userId = request.getParameter("userId");
//        System.out.println(userId+"__________________________________________________________________");
        String RedirectApp = SC_url + "/task/saveTaskNodeMessage";
        String notifyApp = SC_url + "/user/sendMessageToMPOne";
        String FeedBack =  process_proxy_url + "/save_action_operation_self";

        Map<String,String> map = new HashMap<String,String>();
        map.put("userId",userId);
        map.put("taskId",taskId);
        map.put("nodeId","Human_Machine_Thing-1_PerformSelectCoffeeService");
        map.put("url",link);
        map.put("content","Please select the type of Coffee!");
        map.put("instance_id", workflow_instance_id);

        new Thread() {
            public void run() {
                try {
                    //String res = new HttpRequestor().doPost(RedirectApp, map);
                    String res = new HttpRequestor().doPost(notifyApp, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("url", link);
        map1.put("content","Please select the type of Coffee!");
        map1.put("userId", userId);
        map1.put("instance_id", workflow_instance_id);

        new Thread() {
            public void run() {
                try {
                    String res1 = new HttpRequestor().doPost(FeedBack, map1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


        return link;
    }

    @RequestMapping(value = "/getcoffeelink", method = RequestMethod.POST)
    public String getLinkbyPOST(HttpServletRequest request) throws Exception{
        String uri =  "http://" + request.getHeader("Host")  + request.getRequestURI(); // "http://127.0.0.1:8002/s/getcoffeelink";//
        String link = "";
        String regex = "(?<=://)[a-zA-Z\\.0-9]+(:[0-9]+)?(?=\\/)";//"(?<=\\\\/)[a-zA-Z\\\\.:0-9]+(?=\\\\/)"; // 匹配两个斜杠之间的字符串 /.../

        uri = uri.replaceAll(regex, "");
        uri = uri.replaceAll("http(s)?://", "");
        uri = uri.replaceAll("/getcoffeelink", "");
        String[] split_url = uri.split("\\/");
        List<String> list = Arrays.asList(split_url);

        if(split_url.length > 0){
            link = local_url + String.join("/", list) +"/coffee";
        }else{
            link = local_url + "/" + war_name +"/coffee";
        }
        link = local_url + "/" + war_name +"/coffee";


        String taskId = request.getParameter("processIdFromApp");
        workflow_instance_id = request.getParameter("workflow_instance_id");
        instance_id = request.getParameter("instance_id");
        userId = request.getParameter("userId");
//        System.out.println(userId+"__________________________________________________________________");
        String RedirectApp = SC_url + "/task/saveTaskNodeMessage";
        String notifyApp = SC_url + "/user/sendMessageToMPOne";
        String FeedBack =  process_proxy_url + "/save_action_operation_self";

        Map<String,String> map = new HashMap<String,String>();
        map.put("userId", userId);
        map.put("taskId",taskId);
        map.put("nodeId","Human_Machine_Thing-1_PerformSelectCoffeeService");
        map.put("url",link);
        map.put("content","Please select the type of Coffee!");
        map.put("instance_id", workflow_instance_id);

        new Thread() {
            public void run() {
                try {
                    String res = new HttpRequestor().doPost(notifyApp, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("url", link);
        map1.put("content","Please select the type of Coffee!");
        map1.put("userId", userId);
        map1.put("instance_id", workflow_instance_id);

        new Thread() {
            public void run() {
                try {
                    String res1 = new HttpRequestor().doPost(FeedBack, map1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


        return link;
    }
}
