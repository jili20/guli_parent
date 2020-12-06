package com.atguigu.eduservice.entity.chapter;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
/**
 * @author bing  @create 2020/12/5-9:25 下午
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
