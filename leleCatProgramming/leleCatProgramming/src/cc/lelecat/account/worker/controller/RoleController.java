package cc.lelecat.account.worker.controller;
/**
 * 类名：NormalAccountController
 * 功能：实现工作账户—角色 管理控制器
 * 作者：苗怀雨
 * 日期：2016-11-15 10:29:06
 */

import cc.lelecat.account.worker.service.AccountService;
import cc.lelecat.account.worker.service.PowerService;
import cc.lelecat.account.worker.service.RoleService;
import cc.lelecat.entity.account.worker.Account;
import cc.lelecat.entity.account.worker.Power;
import cc.lelecat.entity.account.worker.Role;
import cc.lelecat.entity.school.lesson.Course;
import cc.lelecat.tag.menu.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/account/role")
public class RoleController {

    @Resource
    private AccountService accountService;
    @Resource
    private RoleService roleService;
    @Resource
    private PowerService powerService;

    private String NO_POWER_PAGE = "redirect:/admin/course/listCourse";

    // 管理页标签
    private Item tagManage;
    // 添加页标签
    private Item tagAdd;
    // 标签卡属性Map
    private Map<String, Object> tagsMap = new HashMap<>();

    private String title = "角色管理界面";
    private String description = "在这里您可以管理您的角色.";

    RoleController() {
        // 管理页标签
        tagManage = new Item("管理角色", "/admin/account/role/manage-page", "fa-cog");

        // 添加页标签
        tagAdd = new Item("添加新角色", "/admin/account/role/add-page", "fa-cog");
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

        crumb = new Item("角色管理", null);
        crumbs.add(crumb);

        return crumbs;
    }

    /**
     * 跳转角色管理界面控制器
     *
     * @param session
     * @return
     */
    @RequestMapping("manage-page")
    public String managePage(HttpServletRequest request, HttpSession session) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");

        if (this.powerService.hasRoleManagePower(account.getRole())) {

            // 设置面包屑
            request.setAttribute("crumbs", this.getCrumbs());

            // 设置标签
            request.setAttribute("active_tag", tagManage);
            request.setAttribute("left_tags", tagsMap.get("left_tags"));
            request.setAttribute("right_tags", tagsMap.get("right_tags"));

            List<Role> roles = this.roleService.findAll(account.getRole());
            request.setAttribute("role-list", roles);

            request.setAttribute("title", title);
            request.setAttribute("title-description", description);
            request.setAttribute("description", "选择一个您要管理的角色");

            return "/admin/account/role/manage";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 跳转增加角色页面控制器
     *
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("add-page")
    public String addPage(HttpServletRequest request, HttpSession session) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasRoleManagePower(account.getRole())) {

            // 设置面包屑
            request.setAttribute("crumbs", this.getCrumbs());

            // 设置标签
            request.setAttribute("active_tag", tagAdd);
            request.setAttribute("left_tags", tagsMap.get("left_tags"));
            request.setAttribute("right_tags", tagsMap.get("right_tags"));


            // 设置页面信息
            request.setAttribute("title", title);
            request.setAttribute("title-description", description);
            request.setAttribute("description", "添加一个新的角色");

            request.setAttribute("other-powers", this.powerService.getOtherManagePowers(account.getRole()));
            request.setAttribute("course-powers", this.powerService.getCourseManagePowerMap(account.getRole()));


            return "/admin/account/role/add";
        }

        return NO_POWER_PAGE;
    }

    /**
     * edit-body
     *
     * @param roleId
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("edit-body")
    public String editBody(
            @RequestParam("roleId") Integer roleId,
            HttpServletRequest request,
            HttpSession session
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasRoleManagePower(account.getRole())) {
            Role role = this.roleService.get(roleId);

            request.setAttribute("role", role);
			request.setAttribute("other-powers", this.powerService.getOtherManagePowers(account.getRole()));
			request.setAttribute("course-powers", this.powerService.getCourseManagePowerMap(account.getRole()));

			return "/admin/account/role/edit-body";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 添加角色控制器
     *
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "add")
    public String add(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "coursePowerIds", required = false) Integer[] coursePowerIds,
            @RequestParam(value = "otherPowerIds", required = false) Integer[] otherPowerIds,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasRoleManagePower(account.getRole())) {

            Set<Integer> powerIds = new HashSet<>();
            if (coursePowerIds != null) Collections.addAll(powerIds, coursePowerIds);
            if (otherPowerIds != null) Collections.addAll(powerIds, otherPowerIds);
            String[] info = this.roleService.add(name, description, powerIds, account);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/role/manage-page";
        }

        return NO_POWER_PAGE;

    }


    /**
     * 删除角色控制器
     *
     * @param session
     * @return
     */
    @RequestMapping("delete")
    public String delete(
		@RequestParam("roleId") Integer roleId,
		@RequestParam("password") String password,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasRoleManagePower(account.getRole())) {

            String[] info;

            if (this.accountService.checkPassowrd(account, password))
                info = this.roleService.delete(roleId);
            else
                info = new String[]{"failure", "删除失败, 密码验证错误."};

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/role/manage-page";
        }

        return NO_POWER_PAGE;
    }


    /**
     * 编辑角色控制器
     *
     * @param session
     * @return
     */
    @RequestMapping("edit")
    public String edit(
            @RequestParam("roleId") Integer roleId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam(value = "coursePowerIds", required = false) Integer[] coursePowerIds,
            @RequestParam(value = "otherPowerIds", required = false) Integer[] otherPowerIds,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasRoleManagePower(account.getRole())) {

            Set<Integer> powers = new HashSet<>();
            if (coursePowerIds != null) Collections.addAll(powers, coursePowerIds);
            if (otherPowerIds != null) Collections.addAll(powers, otherPowerIds);
            String[] info = this.roleService.update(roleId, name, description, powers, account);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/role/manage-page";

        }

        return NO_POWER_PAGE;
    }


    /**
     * 禁用角色控制器
     *
     * @param session
     * @return
     */
    @RequestMapping("disable")
    public String disable(
            @RequestParam("roleId") Integer roleId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasRoleManagePower(account.getRole())) {

            String[] info = this.roleService.setRoleDisabled(roleId);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/role/manage-page";
        }

        return NO_POWER_PAGE;
    }

    /**
     * 启用角色控制器
     *
     * @param session
     * @return
     */
    @RequestMapping("enable")
    public String enable(
            @RequestParam("roleId") Integer roleId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        // 判断是否符合权限
        Account account = (Account) session.getAttribute("account");
        if (this.powerService.hasRoleManagePower(account.getRole())) {

            String[] info = this.roleService.setRoleEnabled(roleId);

            redirectAttributes.addFlashAttribute("info", info);

            return "redirect:/admin/account/role/manage-page";
        }

        return NO_POWER_PAGE;

    }
}