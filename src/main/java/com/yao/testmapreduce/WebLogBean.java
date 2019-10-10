package com.yao.testmapreduce;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Calm on 2018/4/25
 * 1.需求:
 * 对web访问日志中的各字段识别切分
 * 去除日志中不合法的记录
 * 根据KPI统计需求,生成各类访问请求过滤数据
 *
 * 2.实现代码:
 * a)定义一个bean,用来记录日志数据中的各数据字段
 *
 * 流量统计相关需求
 * 1.对流量日志中的用户统计总上、下行流量
 * 技术点:自定义JavaBean用来在mapreduce中充当value
 * 注意JavaBean要实现Writable接口,实现两个方法
 */
public class WebLogBean implements Writable{
    long upflow;
    long downflow;
    long sumflow;
    private String remote_addr;//记录客户端的ip地址
    private String remote_user;//记录客户端用户名称,忽略属性"-"
    private String time_local;//记录访问时间与时区
    private String request;//记录请求的url和http协议
    private String status;//记录请求状态;成功是200
    private String body_bytes_sent;//记录发送给客户端文件主体内容大小
    private String http_referer;//用来记录从那个页面链接访问过来的
    private String http_user_agent;//记录客户端浏览的相关信息

    private boolean valid = true;//判断数据是否合法

    public String getRemote_addr() {
        return remote_addr;
    }

    public void setRemote_addr(String remote_addr) {
        this.remote_addr = remote_addr;
    }

    public String getRemote_user() {
        return remote_user;
    }

    public void setRemote_user(String remote_user) {
        this.remote_user = remote_user;
    }

    public String getTime_local() {
        return time_local;
    }

    public void setTime_local(String time_local) {
        this.time_local = time_local;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody_bytes_sent() {
        return body_bytes_sent;
    }

    public void setBody_bytes_sent(String body_bytes_sent) {
        this.body_bytes_sent = body_bytes_sent;
    }

    public String getHttp_referer() {
        return http_referer;
    }

    public void setHttp_referer(String http_referer) {
        this.http_referer = http_referer;
    }

    public String getHttp_user_agent() {
        return http_user_agent;
    }

    public void setHttp_user_agent(String http_user_agent) {
        this.http_user_agent = http_user_agent;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.valid);
        sb.append("\001").append(this.remote_addr);
        sb.append("\001").append(this.remote_user);
        sb.append("\001").append(this.time_local);
        sb.append("\001").append(this.request);
        sb.append("\001").append(this.status);
        sb.append("\001").append(this.body_bytes_sent);
        sb.append("\001").append(this.http_referer);
        sb.append("\001").append(this.http_user_agent);
        return sb.toString();
    }

    //序列化,将对象的字段信息写入输入流
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upflow);
        out.writeLong(downflow);
        out.writeLong(sumflow);
    }

    //反序列化,从输入流中读取各个字段信息
    @Override
    public void readFields(DataInput in) throws IOException {
        upflow = in.readLong();
        downflow = in.readLong();
        sumflow = in.readLong();
    }
}
