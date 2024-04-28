package com.sda.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sda.project.domain.Review;
import org.apache.ibatis.annotations.Mapper;


/**
 *
 * This class is used to operate the database operations for the table【Review】.
 *
 */
@Mapper
public interface ReviewMapper extends BaseMapper<Review> {
}
