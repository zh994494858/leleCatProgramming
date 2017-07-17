package cc.lelecat.account.normal.controller;
/**
 * 类名：NormalAccountController
 * 功能：实现客户账户管理控制器
 * 作者：苗怀雨
 * 日期：2016-11-14 14:38:15
 */

import cc.lelecat.account.normal.service.NormalAccountService;
import cc.lelecat.entity.account.customer.NormalAccount;
import cc.lelecat.tag.menu.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/account/normal-account")
public class NormalAccountController {
    @Resource
    private NormalAccountService normalAccountService;

    final String title = "客户账户管理";
    final String description = "在这里您可以管理客户账户";

    private String NO_POWER_PAGE = "redirect:/admin/course/listCourse";

    // 管理页标签
    private Item tagManage;
    // 添加页标签
    private Item tagAdd;
    private Map<String, Object> tagsMap = new HashMap<>();

    NormalAccountController() {
        // 管理页标签
        tagManage = new Item("管理客户账户", "/admin/account/normal-account/manage-page", "fa-cog");

//        // 添加页标签
//        tagAdd = new Item("添加新账户", "/admin/account/normal-account/add-page", "fa-cog");
//        tagAdd.setCmd("tag-success");

        // 左侧标签
        List<Item> tags = new ArrayList<>();
        tags.add(tagManage);
        tagsMap.put("left_tags", tags);

//        // 右侧标签
//        tags = new ArrayList<>();
//        tags.add(tagAdd);
//        tagsMap.put("right_tags", tags);

    }

    // 生成面包屑
    private List<Item> getCrumbs() {
        // 面包屑
        List<Item> crumbs = new ArrayList<>();
        Item crumb;

        crumb = new Item("主页", "/admin/course/list");
        crumbs.add(crumb);

        crumb = new Item("高级管理", null);
        crumbs.add(crumb);

        crumb = new Item("客户账户管理", null);
        crumbs.add(crumb);

        return crumbs;
    }

    /**
     * 跳转管理客户账户页面控制器
     *
     * @param request
     * @return
     */
    @RequestMapping("manage-page")
    public String mangePage(HttpServletRequest request) {

        // 设置面包屑
        request.setAttribute("crumbs", this.getCrumbs());

        // 设置标签
        request.setAttribute("active_tag", tagManage);
        request.setAttribute("left_tags", tagsMap.get("left_tags"));
        request.setAttribute("right_tags", tagsMap.get("right_tags"));

        List<NormalAccount> accounts = this.normalAccountService.findAll();
        request.setAttribute("account-list", accounts);

        request.setAttribute("title", title);
        request.setAttribute("title-description", description);
        request.setAttribute("description", "选择一个您要管理的客户账户");

        return "/admin/account/normal-account/manage";
    }

//    /**
//     * 跳转增加客户账户页面控制器
//     *
//     * @param session
//     * @param request
//     * @return
//     */
//    @RequestMapping("add-page")
//    public String addPage(HttpServletRequest request, HttpSession session) {
//            // 设置面包屑
//            request.setAttribute("crumbs", this.getCrumbs());
//
//            // 设置标签
//            request.setAttribute("active_tag", tagAdd);
//            request.setAttribute("left_tags", tagsMap.get("left_tags"));
//            request.setAttribute("right_tags", tagsMap.get("right_tags"));
//
//            request.setAttribute("title", title);
//            request.setAttribute("title-description", description);
//            request.setAttribute("description", "增加一个普通账户");
//
//            request.setAttribute("cmd","add");
//
//            return "/admin/account/normal-account/add";
//    }
//
//    /**
//     * 添加客户账户控制器
//     *
//     * @param session
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "add")
//    public String add(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            @RequestParam("name") String name,
//            HttpServletRequest request,
//            HttpSession session
//    ) throws Exception {
//            /*检查用户名和赋予的角色是否符合要求*/
//            String[] info = this.normalAccountService.add(username,password,name);
//
//             request.setAttribute("info", info);
//             return "/admin/account/normal-account/add";
//    }

    /**
     * 删除客户账户控制器
     *
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("delete")
    public String delete(
            @RequestParam("normalAccountId") Integer normalAccountId,
            HttpServletRequest request,
            HttpSession session
    ) throws Exception {
        if (this.normalAccountService.delete(normalAccountId))
            request.setAttribute("info", new Object[]{"success", "删除成功"});
        else
            request.setAttribute("info", new Object[]{"failure", "删除失败, 未知错误, 请重试."});

        return "redirect:/admin/account/normal-account/manage-page";
    }


    /**
     * edit-body
     *
     * @param normalAccountId
     * @param request
     * @param session
     * @return
     */

    @RequestMapping("edit-body")
    public String editBody(
            @RequestParam("normalAccountId") Integer normalAccountId,
            HttpServletRequest request,
            HttpSession session
    ) {

        NormalAccount account = this.normalAccountService.get(normalAccountId);

        request.setAttribute("cmd", "edit");

        request.setAttribute("normalAccountId", normalAccountId);
        request.setAttribute("name", account.getName());

        return "/admin/account/normal-account/edit-body";
    }

    /**
     * 修改客户账户控制器
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("edit")
    public String edit(
            @RequestParam("normalAccountId") Integer normalAccountId,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            HttpServletRequest request
            ) throws Exception {

        NormalAccount acc = this.normalAccountService.get(normalAccountId);
        /*检查用户名和赋予的角色是否符合要求*/
        if (!this.normalAccountService.checkUsernameAvailable(username)) {
            request.setAttribute("info", new Object[]{"failure", "编辑失败, 指定用户名不可用, 请重试"});
        } else {
            acc.setUsername(username);
            acc.setPassword(password);
            acc.setName(name);

            if (this.normalAccountService.update(acc))
                request.setAttribute("info", new Object[]{"success", "编辑成功"});
            else
                request.setAttribute("info", new Object[]{"failure", "编辑失败, 发生未知错误, 请重试"});
        }

        return "redirect:/admin/account/normal-account/manage-page";
    }

    /**
     * 禁用客户账户控制器
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("disable")
    public String disable(
            @RequestParam("normalAccountId") Integer normalAccountId,
            HttpServletRequest request
    ) throws Exception {

        NormalAccount acc = this.normalAccountService.get(normalAccountId);
        acc.setDisabled(true);

        if (this.normalAccountService.update(acc))
            request.setAttribute("info", new Object[]{"success", "禁用成功"});
        else
            request.setAttribute("info", new Object[]{"failure", "禁用失败, 发生未知错误, 请重试"});

        return "redirect:/admin/account/normal-account/manage-page";
    }

    /**
     * 解禁账户页面
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("enable")
    public String enable(
            @RequestParam("normalAccountId") Integer normalAccountId,
            HttpServletRequest request,
            HttpSession session
    ) throws Exception {

        NormalAccount acc = this.normalAccountService.get(normalAccountId);
        acc.setDisabled(false);

        if (this.normalAccountService.update(acc))
            request.setAttribute("info", new Object[]{"success", "启用成功"});
        else
            request.setAttribute("info", new Object[]{"failure", "启用失败, 发生未知错误, 请重试"});

        return "redirect:/admin/account/normal-account/manage-page";
    }
}