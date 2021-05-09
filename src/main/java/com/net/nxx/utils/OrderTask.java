package com.net.nxx.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.net.nxx.model.NxxOrder;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XiaYu
 * @Date 2021/5/9 10:10
 */
public class OrderTask implements Delayed {
    /**
     * 延迟时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private long time;

    private NxxOrder orderModel;

    public OrderTask(){

    }

    public OrderTask(NxxOrder orderModel, long time) {
        this.orderModel = orderModel;
        this.time = System.currentTimeMillis()+1000*time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        OrderTask Order = (OrderTask) o;
        long diff = this.time - Order.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public NxxOrder getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(NxxOrder orderModel) {
        this.orderModel = orderModel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"time\":")
                .append(time);
        sb.append(",\"orderModel\":")
                .append(orderModel);
        sb.append('}');
        return sb.toString();
    }
}
