package cc.lelecat.account.worker.controller;
/**
 * 类名：NormalAccountController
 * 功能：实现工作账户管理控制器
 * 作者：苗怀雨
 * 日期：2016-11-15 10:12:56
 *
 * Modified by ACat,
 * 2016-11-26 14:54:08,
 * ACat i lele.
 */

import cc.lelecat.account.worker.service.AccountService;
import cc.lelecat.account.worker.service.PowerService;
import cc.lelecat.account.worker.service.RoleService;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.tag.menu.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/account/worker-account")
public class AccountController {
    @Resource
    private AccountService accountService;
    @Resource
    private PowerService powerService;
    @Resource
    private RoleService roleService;

    final String title = "工作账户管理";
    final String description = "在这里您可以管理工作账户";

    private String NO_POWER_PAGE = "redirect:/admin/course/listCourse";

    // 管理页标签
    private Item tagManage;
    // 添加页标签
    private Item tagAdd;
    private Map<String, Object> tagsMap = new HashMap<>();

    AccountController() {
        // 管理页标签
        tagManage = new Item("管理工作账户", "/admin/account/worker-account/manage-page", "fa-cog");

        // 添加页标签
        tagAdd = new Item("添加新账户", "/admin/account/worker-account/add-page", "fa-cog");
        tagAdd.setCmd("tag-success");

        // 左侧标签
        List<Item> tags = new ArrayList<>();
        tags.add(tagManage);
        tagsMap.put("left_tags", tags);

        // 右侧标签
        tags = new ArrayList<>();
        tags.add(tagAdd);
        tagsMap.put("right_tags", tags);

    }

    // 生成面包屑
    private List<Item> getCrumbs() {
        // 面包屑
        List<Item> crumbs = new ArrayList<>();
        Item crumb;

        crumb = new Item("主页", "#");
        crumbs.add(crumb);

        crumb = new Item("高级管理", "#");
        crumbs.add(crumb);

        crumb = new Item("工作账户管理", null);
        crumbs.add(crumb);

        return crumbs;
    }

    /**
     * 跳转管理工作账户页面控制器
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("manage-page")
    public String mangePage(HttpServletRequest request, HttpSession session) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {

            // 设置面包屑
            request.setAttribute("crumbs", this.getCrumbs());

            // 设置标签
            request.setAttribute("active_tag", tagManage);
            request.setAttribute("left_tags", tagsMap.get("left_tags"));
            request.setAttribute("right_tags", tagsMap.get("right_tags"));

            List<Account> accounts = this.accountService.findAll(account);
            request.setAttribute("account-list", accounts);

            request.setAttribute("title", title);
            request.setAttribute("title-description", description);
            request.setAttribute("description", "选择一个您要管理的工作账户");

            return "/admin/account/worker-account/manage";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 跳转增加工作账户页面控制器
     *
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("add-page")
    public String addPage(HttpServletRequest request, HttpSession session) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {

            // 设置面包屑
            request.setAttribute("crumbs", this.getCrumbs());

            // 设置标签
            request.setAttribute("active_tag", tagAdd);
            request.setAttribute("left_tags", tagsMap.get("left_tags"));
            request.setAttribute("right_tags", tagsMap.get("right_tags"));

            request.setAttribute("title", title);
            request.setAttribute("title-description", description);
            request.setAttribute("description", "增加一个新的工作账户");

            request.setAttribute("roles", this.roleService.filterAll(account.getRole()));

            return "/admin/account/worker-account/add";
        }

        return NO_POWER_PAGE;
    }

    /**
     * edit-body
     *
     * @param accountId
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("edit-body")
    public String editBody(
        @RequestParam("accountId") Integer accountId,
        HttpServletRequest request,
        HttpSession session
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {
            Account acc = this.accountService.get(accountId);

            request.setAttribute("account", acc);
            request.setAttribute("roles", this.roleService.filterAll(account.getRole()));

            return "/admin/account/worker-account/edit-body";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 添加工作账户控制器
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "add")
    public String add(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("role") Integer roleId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {

            String[] info = this.accountService.add(username, password, name, roleId, account);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/worker-account/manage-page";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 删除工作账户控制器
     *
     * @param session
     * @return
     */
    @RequestMapping("delete")
    public String delete(
            @RequestParam("accountId") Integer accountId,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {

            String[] info;

            if (this.accountService.checkPassowrd(account, password))
                info = this.accountService.delete(accountId);
            else
                info = new String[]{"failure", "删除失败, 密码验证错误."};

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/worker-account/manage-page";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 编辑工作账户控制器
     *
     * @param session
     * @return
     */
    @RequestMapping("edit")
    public String edit(
            @RequestParam("accountId") Integer accountId,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("role") Integer roleId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {

            String[] info = this.accountService.update(accountId, username, password, name, roleId, account);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/worker-account/manage-page";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 禁用工作账户控制器
     *
     * @return
     */
    @RequestMapping("disable")
    public String disaable(
            @RequestParam("accountId") Integer accountId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {

            String[] info = this.accountService.setAccountDisabled(accountId);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/worker-account/manage-page";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 解禁账户页面
     *
     * @return
     */
    @RequestMapping("enable")
    public String enable(
            @RequestParam("accountId") Integer accountId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasWorkerAccountManagePower(account.getRole())) {

            String[] info = this.accountService.setAccountEnabled(accountId);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/worker-account/manage-page";
        }

        return NO_POWER_PAGE;
    }
}