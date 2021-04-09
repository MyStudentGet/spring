#Spring-Aop
##一、基于xml配置步骤
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
##二、基于注解配置步骤        
##三、通知类型
    1、前置通知（在方法执行之前）
        <aop:before>
    2、后置通知（在方法执行之后）
        <aop:after-returning>
    3、环绕通知（在方法执行前后都执行）
        定义方法时要加上参数ProceedingJoinPoint对象，并且在前后增强方法中间加上ProceedingJoinPoint对象.proceed()方法（执行目标方法） 
        <aop:around>  
    4、异常抛出通知（目标方法抛出异常才运行）
        <aop:throwing>
    5、最终通知（无论方法是否有异常都会执行）
        <aop:after>    
        