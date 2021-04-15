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
1、JdbcTemplate开发步骤
----
    1、导入spring-jdbc和spring-tx坐标
    2、创建数据库表和实体类
    3、创建JdbcTemplate对象
    4、设置数据源对象  （知道数据库在哪）
            .setDataSource(数据源对象（需要设置连接参数）)方法
    4、执行数据库操作（增删查改）
           