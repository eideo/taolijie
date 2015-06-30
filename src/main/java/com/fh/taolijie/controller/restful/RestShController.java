package com.fh.taolijie.controller.restful;

import com.fh.taolijie.component.ResponseText;
import com.fh.taolijie.domain.SHPostCategoryModel;
import com.fh.taolijie.domain.SHPostModel;
import com.fh.taolijie.service.ShPostCategoryService;
import com.fh.taolijie.service.ShPostService;
import com.fh.taolijie.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wanghongfei on 15-6-20.
 */
@RestController
@RequestMapping("/api/sh")
public class RestShController {
    @Autowired
    ShPostService shService;
    @Autowired
    ShPostCategoryService cateService;

    @RequestMapping(value = "/list", produces = Constants.Produce.JSON)
    public ResponseText getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                               @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") Integer pageSize) {

        List<SHPostModel> shList = shService.getAllPostList(pageNumber, pageSize, null);

        return new ResponseText(shList);
    }

    /**
     * 根据分类查询sh
     * @param categoryId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/category/{categoryId}", produces = Constants.Produce.JSON)
    public ResponseText getByCategory(@PathVariable("categoryId") Integer categoryId,
                                      @RequestParam(defaultValue = "false") Boolean pageView,
                                      @RequestParam(defaultValue = "0") Integer pageNumber,
                                      @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") Integer pageSize) {

        List<SHPostModel> shList = null;
        if (false == pageView) {
            shService.getPostList(categoryId, pageNumber, pageSize, null);
        } else {
            shList = shService.getAndFilter(categoryId, pageView, pageNumber, pageSize, null);
        }

        return new ResponseText(shList);
    }


    /**
     * 根据用户查询sh
     * @param userId
     * @param filter
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/user/{memberId}", produces = Constants.Produce.JSON)
    public ResponseText getByMember(@PathVariable("memberId") Integer userId,
                                    @RequestParam(defaultValue = "true") Boolean filter,
                                      @RequestParam(defaultValue = "0") Integer pageNumber,
                                      @RequestParam(defaultValue = Constants.PAGE_CAPACITY + "") Integer pageSize) {

        List<SHPostModel> shList = shService.getPostList(userId, filter, pageNumber, pageSize, null);
        return new ResponseText(shList);
    }

    /**
     * 搜索二手信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    public ResponseText search(SHPostModel model) {

        List<SHPostModel> shList = shService.runSearch(model, null);
        return new ResponseText(shList);
    }


    /**
     * 查询所有二手分类
     * @return
     */
    @RequestMapping(value = "/cate/list", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    public ResponseText getCategoryList() {
        List<SHPostCategoryModel> list = cateService.getCategoryList(0, Integer.MAX_VALUE, null);

        return new ResponseText(list);
    }

    /**
     * 根据id查找分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/cate/{id}", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    public ResponseText getCategoryById(@PathVariable Integer id) {
        SHPostCategoryModel cate = cateService.findCategory(id);

        return new ResponseText(cate);
    }
}