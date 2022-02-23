# EmployeeManageSystem
## 员工管理系统
**开发工具及环境: Eclipse、MySQL8.0、JDK1.8、Tomcat9.0**  
-应用JSP+Servlet开发动态web资源，使用MySQL存储员工与部门信息。
-通过Filter过滤器技术实现非登录拦截，使用Session结合EL表达式实现用户信息的动态展示。利用Ajax和form表单向后端进行数据交互。
-应用SQL语句实现了用户多条件查询和模糊查询，Layui进行分页显示。
-项目所应用的Servlet类均继承自BaseServlet父类，BaseServlet类使用反射机制，无需重写doGet、doPost方法，提高了程序的灵活性和扩展性。
-dao层与service层均以接口的形式向外提供功能，降低了程序的耦合性。
