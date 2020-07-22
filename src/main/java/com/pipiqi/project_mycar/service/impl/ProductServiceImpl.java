package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.pipiqi.project_mycar.dao.ProductMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Product;
import com.pipiqi.project_mycar.pojo.ProductExample;
import com.pipiqi.project_mycar.pojo.ProductWithBLOBs;
import com.pipiqi.project_mycar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int addProductCar(ProductWithBLOBs productWithBLOBs) {
        return productMapper.insertSelective(productWithBLOBs);
    }

    @Override
    public void delProductCar(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateProductCar(ProductWithBLOBs productWithBLOBs) {
        return productMapper.updateByPrimaryKeyWithBLOBs(productWithBLOBs);
    }

    @Override
    public ProductWithBLOBs findById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        ProductExample example = new ProductExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<ProductWithBLOBs> productWithBLOBs = productMapper.selectByExampleWithBLOBs(example);
        PageInfo<ProductWithBLOBs> info = new PageInfo<>(productWithBLOBs);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, productWithBLOBs);
        return result;
    }
}
