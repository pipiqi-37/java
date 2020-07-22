package com.pipiqi.project_mycar.controller;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Tag;
import com.pipiqi.project_mycar.service.TagService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/sys/tag/list")
    @ResponseBody
    public DataGridResult findTag(QueryDto queryDto) {
        return tagService.findByPage(queryDto);
    }

    @RequestMapping("/sys/tag/del")
    @ResponseBody
    public R deleteTag(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            tagService.delTag(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/tag/save")
    @ResponseBody
    public R saveTag(@RequestBody Tag tag) {
        int i = tagService.addTag(tag);
        return i > 0 ? R.ok() : R.error("添加失败");
    }

    @RequestMapping("/sys/tag/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id) {
        Tag byId = tagService.findById(id);
        return R.ok().put("tag", byId);
    }

    @RequestMapping("/sys/tag/update")
    @ResponseBody
    public R updateTag(@RequestBody Tag tag) {
        int i = tagService.updateTag(tag);
        return i > 0 ? R.ok() : R.error("更新失败");
    }

    @RequestMapping("/sys/echarts/line")
    @ResponseBody
    public R findLine() {
        return tagService.findLine();
    }

    @RequestMapping("/sys/echarts/bar")
    @ResponseBody
    public R findBar() {
        return tagService.findHistogram();
    }

    @RequestMapping("/sys/echarts/pie")
    @ResponseBody
    public R findPie() {
        return tagService.findPie();
    }
}
