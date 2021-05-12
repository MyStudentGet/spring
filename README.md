一、Spring xml文件的配置
====
bean属性
----
    scope = "prototype" :每次getBean都是一个新的对象（多例模式）
    factory-bean : getBean时找指定的bean（动态工厂实例化，getBean时找工厂对象的某一个方法）
    factory-method : 找某个类中的方法来创建对象（动/静态工厂实例化）
    init-method : 指定类中初始化方法名称（创建对象前会调用）
    destroy-method : 指定类中销毁方法名称（要关闭容器对象才会销毁）
    <property>标签（set方法属性注入）
        <list>(数组数据)
        <map>(键值对)
    <constructor-arg>（构造方法依赖注入）
    <import>导入其他的Spring的分文件（其他xml模块文件）

二、Spring注解
====
1、实例化bean
----
    @Component：使用在类上实例化bean
    @Controller：使用在web层上，实例化Bean
    @Service：使用在service层上，实例化Bean
    @Repository：使用在Dao层上，实例化Bean
2、依赖注入
----
    @Autowired：使用在字段上，依赖注入（根据类型）
    @Qualifier：结合@Autowired一起使用（根据id进行依赖注入）
    @Resource：相当于上面两个组合（按名称注入）
    @Value：注入普通属性（值可以来自.properties文件，不过要先导入该文件（@PropertySource("classpath:名称.properties")））
3、其他注入
----
    @Scope：标志Bean是单例（默认）还是多例（prototype）
    @PostConstruct：使用在方法上（标注该方法是Bean初始化方法）
    @preDestroy：标注该方法是Bean的销毁方法（容器关闭Bean才会销毁）

4、新注解（SpringBoot）
----
    @Configuration：类注解（标志该类是Spring的核心配置类）
    @ComponentScan("包名")：类注解    注解扫描（扫描哪个包）
    @Bean("id名")：方法注解   Spring会将方法的返回值以指定id储存
    @PropertySource("classpath:文件名.properties")：类注解     加载外部properties文件
    @Import(类名.class)：类注解   加载另一个配置文件模块（当有多个模块要导入时：({a.class,b.class,c.class})(可以接受一个数组)）

5、AOP注解
----
    标注当前MyAspect是一个切面类：@Aspect
    抽取切点表达式：
        @Pointcut("execution(* com.itheima.anno.*.*(..))")
    前置增强@Before(切点)：
    后置增强：@AfterReturning(切点)
    环绕增强：@Around(切点)
    异常抛出增强：@AfterThrowing(切点)
    最终增强：@After（切点）
    
    
三、Spring集成Junit步骤（测试容器中的Bean是否注入）
====
1、测试步骤
----
    1）、导入Spring集成Junit的坐标   pom.xml文件中导入坐标
    2）、使用Runwith注解替换原来的运行期  （代码使用Spring提供的内核运行）
        @Runwith(SpringJunit4classRunner.class)
    3）、使用ContextConfiguration   指定配置文件或配置类
        @ContextConfiguration("classpath:文件名.xml")
        @ContextConfiguration(classpath={SpringCofig})
    4）、使用Autowired注入需要测试的对象（要测试哪个Bean就注入哪个）
        @Autowired  // 要测试谁就注入谁
        private UserService userService;
    5）、创建测试方法进行测试
        @Test
        public void 方法名(){}
    
四、Spring-ioc（控制反转） 
====
1、各类实例化比较
----
    1、普通实例化
            <bean id="对象名" class="对象的来源" scope="prototype/singleton(单例或多例)"/>
    
    2、静态工厂实例化（getBeean时直接找“静态工厂类”中的某一方法拿到对象（静态方法无需创建对象））
            <bean id="对象名" class="静态工厂" factory-method="某一方法"/>
    
    3、动态工厂实例化（1、创建工厂对象    2、getBean时找工厂对象的某一个方法）
            <bean id="工厂对象名" class="工厂" />
            <bean id="对象名" factory-bean="工厂对象名" factory-method="某一方法"/>

2、各种依赖注入比较
----
    1、通过set方法依赖注入  把容器中的userDao通过set方法注入到userService中
            <property name="userDao" ref="userDao"></property>
            
    2、通过构造方法依赖注入  name属性为构造参数名
            <constructor-arg name="userDao" ref="userDao"></constructor-arg>
    
    3、如何使用p命令空间注入（使用属性标签代替<property>）
             1)、在顶部加上
                    xmlns:p="http://www.springframework.org/schema/p"
             2)、直接用p:userDao-ref="userDao"命令代替<property>标签     适用于要注入很多数据时使用
                    <bean id="userService" class="com.itheima.service.impl.UserServiceimpl" p:userDao-ref="userDao"/>
    
五、Spring-Aop（面向切面编程）  
===== 
1、基于xml配置步骤
----
    1、配置目标对象
        <bean id="target" class="com.itheima.aop.Target"/>
    2、配置切面对象
    3、配置织入
      <aop:config>
        抽取切点表达式
        <aop:pointcut id="切点表达式id" expression="execution(* com.itheima.aop.*.*(..))"/>
        
        声明切面（切面 = 切点+通知（增强））
        <aop:aspect ref = "切面对象">
            <aop:通知类型 method = "增强方法（切面对象内的）" pointcut-ref="切点表达式id"/>
        </aop:aspect>
2、基于注解配置步骤   
----     
3、通知类型
----
    1、前置通知（在方法执行之前）
        @Before
        <aop:before>
    2、后置通知（在方法执行之后）
        @AfterReturning
        <aop:after-returning>
    3、环绕通知（在方法执行前后都执行）
        定义方法时要加上参数ProceedingJoinPoint对象，并且在前后增强方法中间加上ProceedingJoinPoint对象.proceed()方法（执行目标方法） 
        @Around
        <aop:around>  
    4、异常抛出通知（目标方法抛出异常才运行）
        @AferThrowing
        <aop:throwing>
    5、最终通知（无论方法是否有异常都会执行）
        @After
        <aop:after>    
4、整体AOP开发步骤
----
    1、导入AOP(pom.xml)相关坐标(spring-context、aspectjweaver) 
    2、创建目标接口和目标类（内部有切点（目标方法））
    3、创建切面类（内部有增强方法）
    4、将目标类和切面类的对象创建权交给spring
    5、在applicationContext.xml中(或切面类中使用注解)配置织入关系
    6、在配置文件中开启组件扫描和AOP的自动代理（xml方式省略这一步）
        <!--开启组件扫描-->
        <context:component-scan base-package="com.itheima.anno"/>
    
        <!--aop自动代理-->
        <aop:aspectj-autoproxy/>
    7、测试
六、Spring JdbcTemplate基本使用
====
1、JdbcTemplate（普通）开发步骤
----
    1、导入spring-jdbc和spring-tx坐标
    2、创建数据库表和实体类
    3、创建JdbcTemplate对象
    4、设置数据源对象  （知道数据库在哪）
            JdbcTemplate.setDataSource(数据源对象（需要设置连接参数）)方法
    4、执行数据库操作（增删查改）
2、 JdbcTemplate（xml方式）开发步骤    
----
    1、导入spring-jdbc和spring-tx坐标
    2、引入外部property文件
    2、在xml内配置数据源对象（dataSource  来源c3p0），并设置连接属性（property）
    3、配置jdbc模板对象，并设置相应的数据源对象
            <property name="dataSource" ref="dataSource"/>
    4、测试  
        List<Bean表> tbl_UserList = jdbcTemplate.query("sql语句",new BeanPropertyRowMapper<表名>(类名.class));   
        
3、需要用到的类和方法
----
    1、用模板对象对数据库进行增删改
        jdbcTemplate.update(sql语句,参数1，参数2)
    2、用模板对象对数据库进行查询
        1）、查询多条或单条信息（返回集合）（常用）
            jdbcTemplate.query(sql语句,BeanPropertyRowMapper对象,参数)
            
        2）、查询单条信息（返回一个对象或一个数字）
            .queryForObject(sql语句,BeanPropertyRowMapper对象,参数)
            .queryForObject(sql语句,返回值的类型,参数)
    3、BeanPropertyRowMapper：常应用于使用Spring的JdbcTemplate查询数据库，获取List结果列表，数据库表字段和实体类自动对应。

七、Spring 事务控制（事务回滚）
====

1）、相应api
----
    平台事务管理器(接口，下面有多个实现类)（内部控制事务方式不同，有不同的实现类）:
        PlatformTransactionManager
    
    事务定义对象:TransactionDefinition
        int getIsolationLevel()         获得事务的隔离级别（可以解决 脏读（_READ_UNCOMMITTED）、不可重复读（_COMMITTED）、虚读（幻读）（）（SERIALIZABLE））（ISOLATION_REPEATABLE_READ，能解决所有问题单性能很低）
        int getPropogationBehavior()    获得事务的传播行为（处理方法互相调用时要不要重新创建事务）（REQUIRED:没有事务就新建一个，有事务就加入到这个事务中去 ， SUPPORTS:支持当前事务，如果没有事务就以非事务方式执行）
        int getTimeout()                获得超时时间（默认值是-1，没有超时限制）
        boolean isReadOnly()            是否只读（建议查询时设置只读）
        
    事务状态对象：TransactionStatus
        boolean hasSavepont()           是否存储回滚点
        boolean isCompleted()           事务是否完成
        boolean isNewTransaction()      是否是新事务
        boolean isRollbackOnly()        事务是否回滚      
         
2、基于xml的声明式事务控制（事务回滚）
----
    步骤：
    （平台事务管理器、通知 和 aop织入 基本是配死了（基本不改））
    1）、引入坐标tx、jdbc、mysql驱动以及spring包
    2）、在xml文件顶部写入tx和aop的路径
    3）、配置bean
            数据源（dataSource）
            jdbc模板驱动（jdbcTemplate）
            Dao类（属性来源于模板）
            目标对象类（内有切点）
            平台事务管理器（transactionManager）（使用不同平台管理，具体实现类不同（参考上面的api））
    4）、配置通知（事务哪些增强）
            <tx:advice id="txAdvice" transaction-manager="平台事务管理器">
                
                设置事务的哪些属性
                <tx:attributes>
                
                    哪些方法被增强
                        isolation:隔离级别  propagation:传播行为  timeout:超时时间  read-only:是否只读
                    <tx:method name="*" isolation="DEFAULT" propagation="REQUIRED" timeout="-1" read-only="false"/>
                 </tx:attributes>
            </tx:advice>   
    5）、配置事务的aop织入
            <aop:config>
                    <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.itheima.service.impl.*.*(..))"/>
            </aop:config>
            
3、基于注解的声明式事务控制（事务回滚）
----
    步骤：
    1）引入坐标tx、jdbc、mysql驱动以及spring包
    2）在xml文件顶部写入tx和context的路径
    3）实例化Dao类的bean   使用注解（@Repository）
    4）配置Service类    
           （1）、使用注解（@Service）实例化bean
           （2）、注入属性Dao类（@Resource）
           （3）、配置aop织入（@Transactional）（可加多个参数）（放在类上表示所有方法都用同样的增强，放在方法上表示增强某一方法）
    5）设置组件扫描
            <context:component-scan base-package="com.itheima"/>
    6）配置数据源
    7）配置jdbc模板驱动  
    8）配置平台事务管理器
    9）配置事务的注解驱动（让注解的事务起作用）
            <tx:annotation-driven transaction-manager="transactionManager"/>
                
八、SpringMVC
====

1、开发步骤
----
    1）导入SpringMVC的坐标
    2）配置SpringMVC核心控制器DispathcerServlet（前端控制器）
    3）创建Controller类和视图（jsp）页面
    4）使用注解配置Controller类中的业务方法的映射地址
    5）配置SpringMVC核心文件spring-mvc.xml
    6）客户端发起请求测试
    
2、流程演示
----
    客户端         --请求-->                     tomcat服务器
                                tomcat引擎(前端控制器)                   Web应用
                          1、接受客户请求，解析地址           1、得到前端控制器传过来的req与resp对象）
                          2、创建req对象与resp对象          2、映射访问真实资源（哪个service的哪个方法）
                          3、调用目标资源                   3、真实资源返回视图
           <--响应--       4、获得Web应用传过来的resp的内容       
                            组装成http响应回客户端
                          
3、SpringMVC的执行流程
----
    1）用户发送请求至前端控制器DispatcherServlet.
    2）DispatcherServlet收到请求调用HandlerMapping处理器映射器。
    3）处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet.
    4）DispatcherServlet调用HandlerAdapter处理器适配器。
    5）HandlerAdapter经过适配调用具体的处理器(Controller，叫后端控制器)。
    6）Controller执行完成返回ModelAndView。
    7）HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。
    8）DispatcherServlet将ModelAndView传给ViewReslover视图解析器。
    9）ViewReslover解析后返回具体View.
    10）DispatcherServlet根据View进行渲染视图(即将模型数据填充至视图中)。DispatcherServlet响应用户。

4、SpringMVC注解解析
----   
    @RequestMapping(类标签/方法标签)       设置映射地址（属性有value、method、params）
    @ResponseBody(方法标签)               页面不进行视图跳转，直接进行数据响应（Http方式响应）
                 
                      
5、SpringMVC的数据响应方式
----
    1）页面跳转      （forward（默认，转发）、redirect（重定向））
        1、直接返回字符串（方法返回值类型：String）
        2、通过ModelAndView对象返回
    2）回写数据
        1、直接返回字符串
        2、返回对象或集合                               