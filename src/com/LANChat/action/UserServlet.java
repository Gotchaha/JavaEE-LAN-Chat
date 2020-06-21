package com.LANChat.action;

import com.LANChat.entity.GridData;
import com.LANChat.entity.NhManageDepartment;
import com.LANChat.entity.NhManageUser;
import com.LANChat.service.DepartmentService;
import com.LANChat.service.IDepartmentService;
import com.LANChat.service.IUserService;
import com.LANChat.service.UserService;
import com.LANChat.util.JsonUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    IUserService userService = new UserService();
    IDepartmentService departmentService = new DepartmentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        try {
            //获取当前类的反射对象
            Class c = UserServlet.class;
            //通过反射对象获取当前类的实例
            Object o = c.newInstance();
            //通过反射对象获取需要调用的方法
            Method m = c.getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //反射调用此方法
            m.invoke(o,request,response);
            //1.method参数一定要和方法名完全一样
            //2.方法一定要是public
            //3.方法一定要带2个参数 HttpServletRequest,HttpServletResponse
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(userService.login(username,password)){
            HttpSession session =  request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("role",1);
            request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request,response);//套的主页
        }else{
            request.getRequestDispatcher("/WEB-INF/jsp/user_add_mod.jsp").forward(request,response);//添加
        }
    }
    public void root_login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username_root = request.getParameter("username");
        String password_root= request.getParameter("password");
        if(userService.login(username_root="admin",password_root="root")){

            request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp").forward(request,response);//用户管理
        }else{
            request.getRequestDispatcher("/web/index.jsp").forward(request,response);//返回登录界面
        }
    }

    //进入主页
    public void dotoIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request,response);
    }

    public void addAndModUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        NhManageUser user = new NhManageUser();
        try {
            BeanUtils.populate(user,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(user.getId()!=0){
            userService.update(user);
        }else{
            userService.insert(user);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request,response);
    }

    //进入增加或者修改页面
    public void addAndModPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id!=null && !id.equals("")){
            //修改
            NhManageUser user = userService.findByID(Integer.parseInt(id));
            request.setAttribute("userBean",user);
        }else{
            //增加
        }
        List<NhManageDepartment> departments =departmentService.findAllDepart();
        request.setAttribute("departs",departments);
        request.getRequestDispatcher("/WEB-INF/jsp/user_add_mod.jsp").forward(request,response);

    }
    //进入用户页面
    public void gotoUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp").forward(request,response);
    }

    //获取表格的数据
    public void userList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NhManageUser user = new NhManageUser();
        try {
            BeanUtils.populate(user,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        //获取当前页的数据
        List<NhManageUser> list =userService.findUserByCondition(user);
        //获取总数量
        int count = userService.findCountByCondition(user);
        //使用map
//        Map<String ,Object> map = new HashMap<>();
//        map.put("count",count);
//        map.put("data",list);
//        map.put("msg","获取数据成功");
//        map.put("status",200);
        GridData<NhManageUser> bean = new GridData<>(count,"获取数据成功",200,list);
        String json = JsonUtil.getJsonString4JavaPOJO(bean);
        System.out.println("数据======" + json);
        response.getWriter().print(json);
    }

//    public void delUser(HttpServletRequest request, HttpServletResponse response) {
//        String id = request.getParameter("id");
//        NhManageUser user = new NhManageUser();
//        user.setId(Long.parseLong(id));
//        userService.del(user);
//   }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
