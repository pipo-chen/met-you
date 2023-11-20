package com.metyou.controller.backend;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.User;
import com.metyou.service.ICategoryService;
import com.metyou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("manager/category")
public class CategoryManagerController {
    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "add_category",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName,@RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //增加处理分类的逻辑
            return iCategoryService.addCategory(categoryName, parentId);

        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    /**
     * 修改品类的名称
     * @param session
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //更新categoryName
            return iCategoryService.updateCategoryName(categoryId, categoryName);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_category")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //查询子节点的category信息
            return  iCategoryService.getChildrenParallelCatrgory(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @RequestMapping("get_deep_category")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //查询当前节点的id和递归子节点的id
            return iCategoryService.selectCategoryAndChinldrenById(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

}
