package com.nexus.service.impl;

import com.nexus.mapper.PublishMapper;
import com.nexus.pojo.Page;
import com.nexus.pojo.Publish;
import com.nexus.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    PublishMapper publishMapper;

    @Override
    public void addPublish(Publish publish) {
        publishMapper.addPublish(publish);
    }

    @Override
    public List<Map<String, Object>> publishList(Page page) {
        return publishMapper.publishList(page);
    }

    @Override
    public int total(Page page) {
        return publishMapper.total(page);
    }

    @Override
    public List<Publish> selectPublishTagList() {
        return publishMapper.selectPublishTagList();
    }


//    @Override
//    public List<Publish> publishList() {
//        return publishMapper.publishList();
//    }

}
