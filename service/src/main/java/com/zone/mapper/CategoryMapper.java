package com.zone.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zone.entity.Category;
import org.apache.ibatis.annotations.*;

/**
 * 分类
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    
    /**
     * 插入分类名称
     */
    @Insert("INSERT INTO category (name) VALUES (#{categoryName})")
    Integer addCategory(@Param("categoryName") String categoryName);

    /**
     * 删除分类
     */
    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteCategory(@Param("id") Integer id);

    /**
     * 根据分类名称查询分类
     */
    @Select("SELECT * FROM category WHERE name = #{categoryName}")
    Integer findByCategoryName(@Param("categoryName") String categoryName);
}
