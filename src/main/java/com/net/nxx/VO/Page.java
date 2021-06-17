package com.net.nxx.VO;

import lombok.Data;

import java.util.List;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
@Data
public class Page<T>{
    private List<T> list;
    private int count;

    public Page() {
    }

    public Page(List<T> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        //方便debug的
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"list\":").append(list);
        sb.append(",\"count\":").append(count);
        sb.append('}');
        return sb.toString();
    }
}
