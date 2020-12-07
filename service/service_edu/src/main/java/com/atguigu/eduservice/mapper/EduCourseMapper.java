package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Bing
 * @since 2020-12-05
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    // 根据课程id查询课程确认信息
    public CoursePublishVo getPublishCourseInfo(String courseId);
}
