package com.net.nxx.dao;

import com.net.nxx.model.NxxMessage;

import java.util.List;

public interface NxxMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(NxxMessage record);

    int insertSelective(NxxMessage record);

    NxxMessage selectByPrimaryKey(Long id);

    List<NxxMessage> getMessage(Long userId);

    List<NxxMessage> getIdleMessage(Long idleId);

    int updateByPrimaryKeySelective(NxxMessage record);

    int updateByPrimaryKey(NxxMessage record);
}
