package com.pipiqi.project_mycar.controller;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Message;
import com.pipiqi.project_mycar.service.MessageService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/sys/message/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return messageService.findByPage(queryDto);
    }

    @RequestMapping("/sys/message/del")
    @ResponseBody
    public R delMessage(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            messageService.delMessage(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/message/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Long id) {
        Message byId = messageService.findById(id);
        return R.ok().put("message", byId);
    }

    @RequestMapping("/sys/message/save")
    @ResponseBody
    public R addMessage(@RequestBody Message message) {
        int i = messageService.addMessage(message);
        return i > 0 ? R.ok() : R.error("新增失败!");
    }

    @RequestMapping("/sys/message/update")
    @ResponseBody
    public R updateMessage(@RequestBody Message message) {
        int i = messageService.updateMessage(message);
        return i > 0 ? R.ok() : R.error("新增失败!");
    }
}
