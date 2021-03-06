package com.fh.taolijie.controller.user;

import cn.fh.security.credential.Credential;
import cn.fh.security.utils.CredentialUtils;
import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.component.ResponseText;
import com.fh.taolijie.constant.OperationType;
import com.fh.taolijie.domain.*;
import com.fh.taolijie.service.*;
import com.fh.taolijie.utils.*;
import com.fh.taolijie.utils.json.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wynfrith on 15-6-11.
 */
@Controller
@RequestMapping("/user/job")
public class UJobController {

    @Autowired
    JobPostService jobPostService;
    @Autowired
    JobPostCateService jobPostCateService;
    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;

    @Autowired
    NotificationService notiService;

    /**
     * 我的发布 GET
     *
     * @param session 用户的角色
     * @return
     */
    @RequestMapping(value = "mypost", method = RequestMethod.GET)
    public String myPost(@RequestParam(defaultValue = "0") int page,
                         @RequestParam (defaultValue = Constants.PAGE_CAPACITY + "") int pageSize,
                         HttpSession session, Model model){

        Credential credential = CredentialUtils.getCredential(session);
        page = PageUtils.getFirstResult(page, pageSize);
        List<JobPostModel> jobs = jobPostService.getJobPostListByMember(credential.getId(), page, pageSize)
                .getList();

        int pageStatus = 1;
        if(jobs.size() == 0){
            pageStatus = 0;
        }else if(jobs.size() == pageSize){
            pageStatus = 2;
        }
        model.addAttribute("pageStatus",pageStatus);
        model.addAttribute("jobs",jobs);
        model.addAttribute("page",page);
        //model.addAttribute("totalPage", );
        model.addAttribute("isFav",false);

        return "pc/user/joblist";
    }


    /**
     * 获取已收藏的列表
     * @param page
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "myfav" ,method = RequestMethod.GET)
    public String fav(@RequestParam (defaultValue = "1") int page,
                      @RequestParam (defaultValue = Constants.PAGE_CAPACITY+"") int pageSize,
                      HttpSession session, Model model){
        Credential credential = CredentialUtils.getCredential(session);
        ObjWrapper objWrapper = new ObjWrapper();
        int totalPage = 0;

        List<JobPostModel> jobs = jobPostService.getFavoritePost(credential.getId())
                .getList();

        int pageStatus = 1;
        if(jobs.size() == 0){
            pageStatus = 0;
        }else if(jobs.size() == pageSize){
            pageStatus = 2;
        }
        model.addAttribute("pageStatus",pageStatus);
        model.addAttribute("jobs",jobs);
        model.addAttribute("page",page);
        model.addAttribute("isFav",true);

        return "pc/user/joblist";
    }

    /**
     * 发布兼职页面 get
     * @param
     * @return
     */
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    //region 发布兼职 String post
    public String post(HttpSession session,Model model) {
        int page = 1;
        int pageSize = 9999;
        Credential credential = CredentialUtils.getCredential(session);
        if (credential == null) {
            return "redirect:/login";
        }
        ListResult<JobPostCategoryModel> cateList= jobPostCateService.getCategoryList(page-1, pageSize, new ObjWrapper());
        model.addAttribute("cates",cateList.getList());
        return "pc/user/jobpost";
    }
    //endregion


    /**
     * 修改兼职页面
     * 与发布兼职用同一个页面，只不过修改该兼职会填充好之前的字段
     *
     * @param id 传入要修改的job的id
     * @param session    用户的信息
     * @return
     */
    @RequestMapping(value = "change/{id}", method = RequestMethod.GET)
    //region 修改兼职页面 change
    public String change(@PathVariable int id, HttpSession session,Model model) {
        /**
         * 如果该job不是用户发送的,则返回404
         */
        Credential credential = CredentialUtils.getCredential(session);
        JobPostModel job = jobPostService.findJobPost(id);
        if(job == null|| !ControllerHelper.isCurrentUser(credential, job)){
            return "redirect:/404";
        }

        // 查询兼职分类
        ListResult<JobPostCategoryModel> cateList = jobPostCateService.getCategoryList(0, 100, null);
        model.addAttribute("job", job);
        model.addAttribute("cates", cateList.getList());

        return "pc/user/jobpost";
    }
    //endregion

    /**
     * 发布兼职信息 post ajax
     *
     * @param job
     * @param session
     * @return
     */
    //region 发布兼职 ajax String post
    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String post(@Valid JobPostModel job,
                BindingResult result,
                HttpSession session) {

        if (result.hasErrors()) {
            JsonWrapper jw = new JsonWrapper(false, result.getAllErrors());
            return jw.getAjaxMessage();
        }

        Credential credential = CredentialUtils.getCredential(session);
        String username = credential.getUsername();
        MemberModel mem = accountService.findMember(username, false);

/*        // 检查上次发布的时间间隔
        Date lastJobTime = mem.getLastJobDate();
        Date nowTime = new Date();
        // 如果时间为空，说明这是用户第一次发帖
        if (null != lastJobTime) {
            boolean enoughInterval = TimeUtil.intervalGreaterThan(nowTime, lastJobTime, 1, TimeUnit.MINUTES);

            // 时间间隔少于1min
            // 返回错误信息
            if (false == enoughInterval) {
                return new JsonWrapper(false, "too frequent!").getAjaxMessage();
            }
        }

        // 写入最新的发布时间
        MemberModel memExample = new MemberModel();
        memExample.setId(mem.getId());
        memExample.setLastJobDate(nowTime);
        accountService.updateMember(memExample);*/

        /*创建兼职信息*/
        job.setMemberId(credential.getId());
        job.setPostTime(new Date());
        job.setLikes(0);
        job.setDislikes(0);
        job.setComplaint(0);

        if (job.getJobPostCategoryId() != null) {
            jobPostService.addJobPost(job);
            // 加分
            Integer credits = userService.changeCredits(mem.getId(), OperationType.POST, mem.getCredits());
            //LogUtils.getInfoLogger().info("---new credits:{}, level:{}", credits, userService.queryLevel(credits));
        } else {
            return new JsonWrapper(false,Constants.ErrorType.PARAM_ILLEGAL).getAjaxMessage();
        }

        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();
    }
    //endregion


    /**
     * 删除兼职 post ajax
     *
     * @param session
     * @return
     */
    //region 删除兼职 ajax String post
    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String delPost(@PathVariable  int id,
                   @RequestParam(required = false) String ids,
                HttpSession session) {

        int uid= CredentialUtils.getCredential(session).getId();

        String [] delIds = {id+""};
        //id=0视为多条删除
        if(id==0){
            delIds = ids.split(";");
        }


        for(String currId:delIds){
            //查找兼这条兼职是不是用户发布的
            JobPostModel job =jobPostService.findJobPost(Integer.parseInt(currId));
            if(job == null){
                return new JsonWrapper(false, Constants.ErrorType.NOT_FOUND).getAjaxMessage();
            }
            if(job.getMember().getId()!=uid){
                return new JsonWrapper(false, Constants.ErrorType.PERMISSION_ERROR).getAjaxMessage();
            }
            //删除兼职
            jobPostService.deleteJobPost(Integer.parseInt(currId));
        }


        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();
    }
    //endregion



    /**
     * 收藏一条兼职
     */
    @RequestMapping(value = "/fav/{id}",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    //region 收藏一条兼职 fav
    public @ResponseBody String fav (@PathVariable int id,
                                     @RequestParam(required = false) String ids,
                                     HttpSession session){
        Credential credential = CredentialUtils.getCredential(session);
        if(credential == null)
            return  new JsonWrapper(false, Constants.ErrorType.PERMISSION_ERROR).getAjaxMessage();
//        MemberModel mem = accountService.findMember(credential.getId());


        String [] delIds = {id+""};
        //id=0视为多条删除
        if(id==0){
            delIds = ids.split(";");
        }


        String status ="1";
        for(String currId:delIds){
            if(jobPostService.findJobPost(Integer.parseInt(currId)) == null)
                return  new JsonWrapper(false, Constants.ErrorType.NOT_FOUND).getAjaxMessage();

            //遍历用户的收藏列表
            //如果没有这条兼职则添加,反之删除

            boolean isFav = jobPostService.isPostFavorite(credential.getId(),Integer.parseInt(currId));

            if(isFav){ //找到,删除收藏
                jobPostService.unfavoritePost(credential.getId(),Integer.parseInt(currId));
                status = "1";
            }else{ //没有找到,则添加收藏
                jobPostService.favoritePost(credential.getId(),Integer.parseInt(currId));
                status = "0";
            }
        }




        return new JsonWrapper(true, "status",status).getAjaxMessage();
    }
    //endregion


    /**
     * 取消收藏一条兼职 或多条
     */
    @RequestMapping(value = "/fav/del",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public @ResponseBody String fav (HttpSession session,
                                     @RequestParam(required = false) String ids){
        Credential credential = CredentialUtils.getCredential(session);

        /*删除一个或多个*/
        try{
            for(String i : ids.split(";")){
                int currId = Integer.parseInt(i);
                jobPostService.unfavoritePost(credential.getId(), currId);
            }
        }catch (Exception e){
            System.out.println("mutideleteError");
            return new JsonWrapper(false, Constants.ErrorType.FAILED).getAjaxMessage();
        }
        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();
    }

    /**
     * 赞
     * @param jobId
     * @param session
     * @return
     */
    @RequestMapping(value = "/{id}/like", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    @ResponseBody
    public String likeJob(@PathVariable("id") Integer jobId,
                          HttpSession session) {
        // 判断是否重复赞
        Credential cre = CredentialUtils.getCredential(session);
        if (null == cre) {
            return new JsonWrapper(false, "not logged in now!").getAjaxMessage();
        }
        Integer userId = cre.getId();
        boolean liked = userService.isJobPostAlreadyLiked(userId, jobId);
        if (liked) {
            return new JsonWrapper(false, "already liked").getAjaxMessage();
        }

        userService.likeJobPost(userId, jobId);

        // 加分
        MemberModel mem = jobPostService.findJobPost(jobId).getMember();
        userService.changeCredits(mem.getId(), OperationType.LIKE, mem.getCredits());

        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();
    }

    /**
     * 取消赞
     * @return
     */
    @RequestMapping(value = "/{id}/unlike", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    @ResponseBody
    public String unlikeJob(@PathVariable("id") Integer jobId,
                                  HttpSession session) {
        // 登陆判断
        Credential cre = CredentialUtils.getCredential(session);
        if (null == cre) {
            return new JsonWrapper(false, "not logged in now!").getAjaxMessage();
        }

        // 执行操作
        boolean opsResult = userService.unLikeJobPost(cre.getId(), jobId);
        // 返回false说明用户本来就没有点过赞
        if (false == opsResult) {
            return new JsonWrapper(false, "invalid operation!").getAjaxMessage();
        }

        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();

    }

    /**
     * 检查是否已赞
     * @return
     */
    @RequestMapping(value = "/{id}/checklike", method = RequestMethod.GET, produces = Constants.Produce.JSON)
    @ResponseBody
    public String checkLike(@PathVariable("id") Integer jobId,
                            HttpSession session) {
        // 登陆判断
        Credential cre = CredentialUtils.getCredential(session);
        if (null == cre) {
            return new JsonWrapper(false, "not logged in now!").getAjaxMessage();
        }

        boolean liked = userService.isJobPostAlreadyLiked(cre.getId(), jobId);

        return new JsonWrapper(true, Boolean.toString(liked)).getAjaxMessage();
    }

    /**
     * 举报一条兼职
     */
    @RequestMapping(value = "/complaint/{id}", method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    public @ResponseBody String complaint(HttpSession session,@PathVariable int id){
        //TODO:限定举报数目
        Credential credential = CredentialUtils.getCredential(session);
        if(credential == null){
            return new JsonWrapper(false, Constants.ErrorType.NOT_LOGGED_IN).getAjaxMessage();
        }
        try{
            jobPostService.complaint(id);
        }
        catch(Exception e){
            System.out.println(e);
            return new JsonWrapper(false, Constants.ErrorType.ERROR).getAjaxMessage();
        }
        return new JsonWrapper(true,Constants.ErrorType.SUCCESS).getAjaxMessage();
    }

    /**
     * 更新兼职信息
     * @return
     */
    @RequestMapping(value = "/change/{jobId}",method = RequestMethod.POST, produces = Constants.Produce.JSON)
    public @ResponseBody String change(@PathVariable("jobId") Integer jobId,
                                       JobPostModel jobPostModel,
                                       HttpSession session,
                                       HttpServletResponse resp){
        Credential credential = CredentialUtils.getCredential(session);

        if (null == jobPostModel) {
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return new JsonWrapper(false, Constants.ErrorType.USER_INVALID_ERROR).getAjaxMessage();
        }

        // 检查是不是本用户发布的信息
        jobPostModel.setId(jobId);
        JobPostModel job = jobPostService.findJobPost(jobPostModel.getId());
        if (null == job) {
            return new JsonWrapper(false, Constants.ErrorType.USER_INVALID_ERROR).getAjaxMessage();
        }

        // 更新信息
        boolean operationResult = jobPostService.updateJobPost(jobPostModel.getId(), jobPostModel);
        if (false == operationResult) {
            return new JsonWrapper(false, "update failed").getAjaxMessage();
        }

        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();
    }

    /**
     * 刷新兼职数据 ajax
     * 更新一下posttime
     *
     * @param id      兼职的id
     * @param session 用户的信息
     */
    @RequestMapping(value = "refresh/{id}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    String refresh(@PathVariable int id, HttpSession session) {
        /**
         * 如果该job不是用户发送的,则错误json
         */
        Credential credential = CredentialUtils.getCredential(session);
        JobPostModel job = jobPostService.findJobPost(id);
        if(job == null) {
            return new JsonWrapper(false, Constants.ErrorType.NOT_FOUND).getAjaxMessage();
        }

        if(!ControllerHelper.isCurrentUser(credential,job)){
            return  new JsonWrapper(false, Constants.ErrorType.PERMISSION_ERROR).getAjaxMessage();
        }

        job.setPostTime(new Date());
        if(!jobPostService.updateJobPost(job.getId(),job)){
            return new JsonWrapper(false, Constants.ErrorType.ERROR).getAjaxMessage();
        }

        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();
    }

    /**
     * 发表评论
     * @param jobId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "{jobId}/review/post", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    @ResponseBody
    public String postReview(@PathVariable("jobId") Integer jobId,
                             ReviewModel model,
                             HttpSession session) {
        Credential credential = CredentialUtils.getCredential(session);
        if (null == credential) {
            return new JsonWrapper(false, "未登陆").getAjaxMessage();
        }

        model.setMemberId(credential.getId());
        model.setJobPostId(jobId);
        model.setTime(new Date());

        reviewService.addReview(model);
        Integer newReviewId = model.getId();

        // 发送被评论通知
        // 得到兼职的发送者
        JobPostModel job = jobPostService.findJobPost(jobId);
        Integer toMemberId = job.getMemberId();
        // 创建通知实体
        PrivateNotificationModel priNoti = new PrivateNotificationModel();
        priNoti.setToMemberId(toMemberId);
        priNoti.setContent("有人评论了你的[" + job.getTitle() + "]");
        priNoti.setTime(new Date());
        // 保存到db
        notiService.addNotification(priNoti);


        return new JsonWrapper(true, "reviewId", newReviewId.toString()).getAjaxMessage();
    }

    /**
     * 删除评论
     * @return
     */
    @RequestMapping(value = "{jobId}/review/delete/{reviewId}", method = RequestMethod.POST, produces = Constants.Produce.JSON)
    @ResponseBody
    public String deleteReview(@PathVariable("jobId") Integer jobId,
                               @PathVariable("reviewId") Integer reviewId,
                               HttpSession session
                               ) {
        // 判断是不是自己发的评论
        Integer curUserId = CredentialUtils.getCredential(session).getId();
        Integer targetUserId = reviewService.getById(reviewId).getMember().getId();
        if (false == curUserId.equals(targetUserId)) {
            return new JsonWrapper(false, "非法操作").getAjaxMessage();
        }

        // 删除评论
        reviewService.deleteReview(reviewId);
        // 删除评论回复
        reviewService.deleteReplyByReview(reviewId);

        return new JsonWrapper(true, Constants.ErrorType.SUCCESS).getAjaxMessage();
    }


}
