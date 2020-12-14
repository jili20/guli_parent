package com.atguigu.educms.controller;

import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.atguigu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 轮播图 前台显示
 * @author bing  @create 2020/12/9-11:26 上午
 */
@RestController
@RequestMapping("/educms/bannerfront")
//@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    // 查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}

