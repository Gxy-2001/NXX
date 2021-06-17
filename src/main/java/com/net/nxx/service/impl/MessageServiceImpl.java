package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxIdleItemDao;
import com.net.nxx.dao.NxxMessageDao;
import com.net.nxx.dao.NxxUserDao;
import com.net.nxx.model.NxxIdleItem;
import com.net.nxx.model.NxxMessage;
import com.net.nxx.model.NxxUser;
import com.net.nxx.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-03
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private NxxMessageDao messageDao;

    @Resource
    private NxxUserDao userDao;

    @Resource
    private NxxIdleItemDao idleItemDao;

    /**
     * 增加留言
     *
     * @param nxxMessage
     * @return
     */
    @Override
    public boolean addMessage(NxxMessage nxxMessage) {
        return messageDao.insert(nxxMessage) == 1;
    }

    /**
     * 删除一条留言
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteMessage(Long id) {
        return messageDao.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 获取一条留言
     *
     * @param id
     * @return
     */
    @Override
    public NxxMessage getMessage(Long id) {
        return messageDao.selectByPrimaryKey(id);
    }

    /**
     * 获取一个用户收到的所有留言
     *
     * @param userId
     * @return
     */
    @Override
    public List<NxxMessage> getAllMessage(Long userId) {
        List<NxxMessage> list = messageDao.getMessage(userId);
        if (list.size() > 0) {
            // 拿到自己所有留言的来源用户
            System.out.println("我拿到一些message了");
            List<Long> idList = new ArrayList<>();
            for (NxxMessage i : list) {
                idList.add(i.getUserId());
            }
            List<NxxUser> userList = userDao.findUserByList(idList);
            Map<Long, NxxUser> userMap = new HashMap<>();
            for (NxxUser user : userList) {
                userMap.put(user.getId(), user);
            }
            for (NxxMessage i : list) {
                i.setFrom(userMap.get(i.getUserId()));
            }

            // 拿到每条消息对应的idle
            List<Long> idleIdList = new ArrayList<>();
            for (NxxMessage i : list) {
                idleIdList.add(i.getIdleId());
            }
            List<NxxIdleItem> idleList = idleItemDao.findIdleByList(idleIdList);
            Map<Long, NxxIdleItem> idleMap = new HashMap<>();
            for (NxxIdleItem idle : idleList) {
                idleMap.put(idle.getId(), idle);
            }
            for (NxxMessage i : list) {
                i.setIdle(idleMap.get(i.getIdleId()));
                System.out.println(i);
            }
        }
        return list;
    }

    /**
     * 查询一个闲置下的所有留言
     *
     * @param idleId
     * @return
     */
    @Override
    public List<NxxMessage> getAllIdleMessage(Long idleId) {
        List<NxxMessage> list = messageDao.getIdleMessage(idleId);
        if (list.size() > 0) {
            List<Long> idList = new ArrayList<>();
            for (NxxMessage i : list) {
                idList.add(i.getUserId());
            }
            List<NxxUser> userList = userDao.findUserByList(idList);
            Map<Long, NxxUser> map = new HashMap<>();
            for (NxxUser user : userList) {
                map.put(user.getId(), user);
            }
            for (NxxMessage i : list) {
                i.setFrom(map.get(i.getUserId()));
            }
            Map<Long, NxxMessage> mesMap = new HashMap<>();
            for (NxxMessage i : list) {
                mesMap.put(i.getId(), i);
            }
            for (NxxMessage i : list) {
                NxxMessage toM = new NxxMessage();
                NxxUser toU = new NxxUser();
                if (i.getToMessage() != null) {
                    toM.setContent(mesMap.get(i.getToMessage()).getContent());
                    toU.setNickname(map.get(i.getToUser()).getNickname());
                }
                i.setToM(toM);
                i.setToU(toU);
            }
        }
        return list;
    }
}
