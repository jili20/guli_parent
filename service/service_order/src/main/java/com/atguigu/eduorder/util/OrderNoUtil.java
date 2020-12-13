package com.atguigu.eduorder.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * @author bing  @create 2020/12/12-9:15 下午
 */
public class OrderNoUtil {
    /**
     * 获取订单号
     * @return
     */
    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }

}
