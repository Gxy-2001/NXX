package com.net.nxx.model;

import java.util.List;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
public class Page<E>{
    private List<E> list;
    private int count;

    public Page() {
    }

    public Page(List<E> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
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
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"list\":")
                .append(list);
        sb.append(",\"count\":")
                .append(count);
        sb.append('}');
        return sb.toString();
    }
}
