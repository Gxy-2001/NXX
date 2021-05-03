package com.net.nxx.service;

import com.net.nxx.model.NxxMessage;

import java.util.List;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-03
 */
public interface MessageService {
    /**
     * 发送留言
     * @param nxxMessage
     * @return
     */
    boolean addMessage(NxxMessage nxxMessage);

    /**
     * 删除留言
     * @param id
     * @return
     */
    boolean deleteMessage(Long id);

    /**
     * 获取某个留言
     * @param id
     * @return
     */
    NxxMessage getMessage(Long id);

    /**
     * 获取某个用户收到的所有留言
     * @param userId
     * @return
     */
    List<NxxMessage> getAllMessage(Long userId);

    /**
     * 获取某个闲置的所有留言
     * @param idleId
     * @return
     */
    List<NxxMessage> getAllIdleMessage(Long idleId);
}
