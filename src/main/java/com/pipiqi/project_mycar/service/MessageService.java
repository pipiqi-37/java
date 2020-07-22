package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Message;

public interface MessageService {
    public int addMessage(Message message);

    public void delMessage(Long id);

    public int updateMessage(Message message);

    public Message findById(Long id);

    public DataGridResult findByPage(QueryDto queryDto);

}
