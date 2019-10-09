package com.yao.testmapreduce;

/**
 * Created by Calm on 2018/4/25
 * b)定义一个parser用来解析过滤web访问日志原始记录
 */
public class WebLogParser {
    public static WebLogBean parser(String line){
        WebLogBean webLogBean = new WebLogBean();
        String[] arr = line.split(" ");
        if(arr.length > 11){
            webLogBean.setRemote_addr(arr[0]);
            webLogBean.setRemote_user(arr[1]);
            webLogBean.setTime_local(arr[3].substring(1));
            webLogBean.setRequest(arr[6]);
            webLogBean.setStatus(arr[8]);
            webLogBean.setBody_bytes_sent(arr[9]);
            webLogBean.setHttp_referer(arr[10]);

            if(arr.length > 12){
                webLogBean.setHttp_user_agent(arr[11] + " " + arr[12]);
            }else {
                webLogBean.setHttp_user_agent(arr[11]);
            }

            if(Integer.parseInt(webLogBean.getStatus()) >= 400){//大于400,HTTP错误
                webLogBean.setValid(false);
            }
        }else {
            webLogBean.setValid(false);
        }
        return webLogBean;
    }

    public static String parserTime(String time){
        time.replace("/","-");
        return time;
    }

}
