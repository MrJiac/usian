package com.usian.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usian.mapper.TbItemMapper;
import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemExample;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    public TbItem selectItemInfo(Long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }

    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.setOrderByClause("updated DESC");
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andStatusEqualTo((byte)1);
        List<TbItem> tbItemList=itemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> PageInfo = new PageInfo<>(tbItemList);
        //返回PageResult
        PageResult pageResult = new PageResult();
        pageResult.setResult(PageInfo.getList());

        pageResult.setTotalPage(Long.valueOf(PageInfo.getPages()));
        pageResult.setPageIndex(PageInfo.getPageNum());
        return pageResult;
    }
}
