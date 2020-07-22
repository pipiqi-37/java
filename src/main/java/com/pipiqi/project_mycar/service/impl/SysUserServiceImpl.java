package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.SysUserMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.SysUser;
import com.pipiqi.project_mycar.service.SysUserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectByExample(null);
    }

    @Override
    public DataGridResult findUserByPage(QueryDto queryDto) {
        // 分页
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        // 设置排序字段
        if (queryDto.getSort() != null && !queryDto.getSort().equals("")) {
            queryDto.setSort("user_id");
        }
        // 获得查询到的集合
        List<SysUser> userByPage = sysUserMapper.findUserByPage(queryDto);
        // 封装
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(userByPage);
        long total = pageInfo.getTotal();
        DataGridResult result = new DataGridResult(total, pageInfo.getList());
        return result;
    }

    @Override
    public Workbook exportUser() {
        // 1.创建一个空的excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2.填充数据,创建sheet
        HSSFSheet sheet = workbook.createSheet("员工信息");
        // 3.标题数组
        String titles[] = {"用户ID", "用户名", "邮箱", "电话", "创建时间"};
        String colums[] = {"userId", "username", "email", "mobile", "createTime"};
        // 4.查询数据库数据并填充
        List<Map<String, Object>> maps = sysUserMapper.exportUser();

        Row rowTile = sheet.createRow(0);
        // 标题行
        for (int i = 0; i < titles.length; i++) {
            Cell cell = rowTile.createCell(i);
            cell.setCellValue(titles[i]);
        }
        // 遍历数据填充到单元格
        for (int i = 0; i <maps.size() ; i++) {
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < titles.length; j++) {
                HSSFCell cell = row.createCell(j);
                Map<String, Object> rowValue = maps.get(i);
                Object o = rowValue.get(colums[j]);
                cell.setCellValue(o + "");
            }
        }
        return workbook;
    }

    @Override
    public SysUser findUserInfo(String username) {
        return sysUserMapper.findUserInfo(username);
    }

}
