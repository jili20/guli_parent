package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Bing
 * @since 2020-12-04
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //获取文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 封装一级分类的条件，根据条件查出所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        // 封装二级分类的条件，根据条件查出所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        // 创建容器，存入所有一级分类、二级分类
        List<OneSubject> finalSubjectList = new ArrayList<>();

        // 遍历上面查出来的一级分类集合，取出每一个一级分类对象，放到一级分类对象中
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject, oneSubject);
            finalSubjectList.add(oneSubject);

            // 创建存放二级分类的容器
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            // 遍历所有二级分类（条件是父 id 等于一级分类的 id），存入到二级分类容器中
            for (int m = 0; m < twoSubjectList.size(); m++) {
                EduSubject tSubject = twoSubjectList.get(m);
                if (tSubject.getParentId().equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            // 把遍历后的二级分类集合，赋值给一级分类的 children 属性
            oneSubject.setChildren(twoFinalSubjectList);
        }
        // 返回一级分类容器收集的所有数据
        return finalSubjectList;
    }

//    @Override
//    public List<OneSubject> getAllOneTwoSubject() {
//        //1 查询所有一级分类  parentid = 0
//        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
//        wrapperOne.eq("parent_id", "0");
//        // 查询所有一级分类；在此类，底层封装了 baseMapper，直接拿来用；
//        // 另一种方式：this.list(wrapperOne);
//        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
//
//        //2 查询所有二级分类  parentid != 0
//        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
//        wrapperTwo.ne("parent_id", "0");
//        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
//
//        //创建list集合，用于存储最终封装数据
//        List<OneSubject> finalSubjectList = new ArrayList<>();
//
//        //3 封装一级分类
//        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
//        //封装到要求的list集合里面 List<OneSubject> finalSubjectList
//        for (int i = 0; i < oneSubjectList.size(); i++) { //遍历oneSubjectList集合
//            //得到oneSubjectList每个eduSubject对象
//            EduSubject eduSubject = oneSubjectList.get(i);
//            //把eduSubject里面值获取出来，放到OneSubject对象里面
//            OneSubject oneSubject = new OneSubject();
//            // oneSubject.setId(eduSubject.getId());
//            // oneSubject.setTitle(eduSubject.getTitle());
//            //eduSubject值（放到）复制到对应oneSubject对象里面
//            BeanUtils.copyProperties(eduSubject, oneSubject);
//            //多个OneSubject放到finalSubjectList里面
//            finalSubjectList.add(oneSubject);
//
//            //在一级分类循环遍历查询所有的二级分类
//            //创建list集合封装每个一级分类的二级分类
//            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
//            //遍历二级分类list集合
//            for (int m = 0; m < twoSubjectList.size(); m++) {
//                //获取每个二级分类
//                EduSubject tSubject = twoSubjectList.get(m);
//                //判断二级分类parentid和一级分类id是否一样
//                if (tSubject.getParentId().equals(eduSubject.getId())) {
//                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
//                    TwoSubject twoSubject = new TwoSubject();
//                    BeanUtils.copyProperties(tSubject, twoSubject);
//                    twoFinalSubjectList.add(twoSubject);
//                }
//            }
//            //把一级下面所有二级分类放到一级分类里面
//            oneSubject.setChildren(twoFinalSubjectList);
//        }
//        return finalSubjectList;
//    }
}
