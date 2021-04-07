package com.nexus.mapper;

import com.nexus.pojo.Page;
import com.nexus.pojo.Publish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PublishMapper {
    // 新增文章
   void addPublish(Publish publish);

    // 查询发布的内容
//    List<Publish> publishList();
    List<Map<String, Object>> publishList(Page page);

    // 统计总条数
    int total(Page page);

    // 查询标签列表
    List<Publish> selectPublishTagList();
}
