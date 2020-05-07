# Queen
重学springboot，体会传统的springmvc与springboot的区别，同时巩固spring知识

##boot 工程启动类<br>
要运行SpringBoot应用程序有几种方式，其中包含传统的war文件部署。但是启动类里的main()方法让你可以在
命令行里把该应用程序当作一个可执行的jar文件来运行
##boot工程启动类注解 @SpringBootApplication 详解<br>
@SpringBootApplication=@Configuration+@EnableAutoConfiguration+@ComponentScan<br>
@Configuration：标明该启动类使用Spring基于Java的配置。
该注解的作用是开启组件扫描和自动配置，注意：扫描的范围为启动类所在的包和子包。<br>
如果我们需要扫描通过maven依赖添加的jar，我们就要单独使用@ComponentScan注解扫描第三方包。<br>
如果启动类中同时存在@ComponentScan和@SpringBootApplication，那么@SpringBootApplication注解
扫描的作用将会失效，也就是不能扫描启动类所在包以及子包了。因此，我们必须在@ComponentScan注解中
配置启动类所在的包<br>
##覆盖自动配置<br>
如何做： 覆盖自动配置的最简单的方式，直接显式地写一段配置。如：我可以显式的写一段通过druid生成
数据库连接池的代码,这样boot就不会通过自动配置来为你创建连接池了。<br>
揭开自动配置的面纱：SpringBoot的自动配置使用了spring4.0的条件化配置，可以在运行时判断这个自动
配置是该被运用还是被忽略。例：如果在自动配置上含有 @ConditionalOnMissingBean 注解，意味着当
不存在该类型的bean时，才会执行该自动配置，否则不会。<br>
注意：SpringBoot是先加载应用级配置（自己写的），然后才是加载自动配置。<br>
##通过属性文件外置配置<br>
要覆盖自动配置我们并不需要每次都显示的写一段代码来覆盖。例如：为了设置数据库的url，我们只需要
通过配置一个属性即可，无需手动申明一个连接池的bean。<br>
SpringBoot _自动配置_ 有多种途径设置属性：<br>
1)命令行参数 如：java -jar readinglist-0.0.1-SNAPSHOT.jar --spring.main.show-banner=false <br>
2)java:comp/env里的JNDI属性<br>
3)JVM系统属性<br>
4)操作系统环境变量<br>
5)随机生成的带random.*前缀的属性（在设置其他属性时，可以引用它们，比如${random. long}）<br>
6)应用程序以外的application.properties或者appliaction.yml文件 <br>
7)打包在应用程序以内的application.properties或者appliaction.yml文件<br>
8)通过@PropertySource标注的属性源<br>
9)默认属性<br>
这个列表按优先级从高到低排列，任何高优先级属性源里设置的属性都会覆盖低优先级的相同属性<br>

application.properties和appliaction.yml文件能放在以下四个位置:<br>
1)外置，在相对于应用程序运行目录的/config子目录里。(应用程序运行的目录：比如你的boot工程打成
jar包，放在G：\work 目录下，那么 G:\work 就是程序运行目录)<br>
2)外置，在应用程序运行的目录里。<br>
3)内置，在config包里。<br>
4)内置，在Classpath根目录。<br>
同样，这个列表按照优先级从高到低排列。此外，如果你在同一优先级位置同时有application.properties
和appliaction.yml，那么appliaction.yml的优先级更高<br>

## @ConfigurationProperties<br>
使用该注解，能够将配置文件里对应的值注入到类的属性中。注意@ConfigurationProperties注解得在@Component
或@Controller或@Service等一系列能创建bean的注解下。属性值注入时确保该属性有 _set方法_。
@ConfigurationProperties默认是从application.yml或application.properties中取值的。
如果你想从其它文件中取值，如classpath下的sysconfig.properties中取值，则再在该类上添加
@PropertySource(value= "classpath:sysconfig.properties",encoding = "utf-8")注解即可
注意：如果此处你的properties读出来的中文乱码，则确保你的properties文件是以utf-8编码的，idea中
在设置里搜file encoding 就能看到你的properties文件的编码方式，以什么编码就以什么解码。

从技术上来说，@ConfigurationProperties注解不会生效，除非先向spring配置类添加@EnableConfigurationProperties
但通常无需这么做，因为springboot自动配置后面的全部配置类都已经加上了@EnableConfigurationProperties注解<br>

##使用profile进行配置<br>
当应用程序需要部署到不同的运行环境时，一些配置细节通常会有所不同。Profile是一种条件化配置，基于运行时
激活的profile，会使用或忽略不同的Bean或配置类。
如何指定自动配置的profile：在我们自己显式创建的bean中，我们可以通过 @Profile 注解来指定该bean适用于
什么环境。但是如果是springboot自动配置创建的bean，我们是无法添加 @Profile 注解的。我们只需要在基础的
配置文件（application.yml或application.properties） 设置 spring.profiles.active = xxx ,然后再
创建 application-{xxx}.yml 或 application-{xxx}.properties 的配置文件，将与xxx环境有关的配置写到
application-{xxx}.yml 或 application-{xxx}.properties 中即可。而基础的配置文件中我们可以写与xxx
环境无关的配置。
如果你使用的基础配置文件是yml，我们也可以将所有的xxx的配置属性都放在一个application.yml里。我们只需要
使用 --- 作为分隔符，对于特定的xxx的配置，我们只需要在分隔符下设置的spring.profiles 为xxx 并配置与xxx
环境有关的配置即可。<br>

##定制应用程序错误页面<br>
Springboot会默认提供白标错误页，不过比较丑，我们可以自定义一个好看的错误页并覆盖掉它。我们只需要自己
创建一个错误页，并把它命名为 error.xxx 即可。其中，xxx后缀与你的视图解析器有关，如果你使用的是jsp视图
则命名为 error.jsp 如果是thymeleaf，则命名为 error.html即可<br>

##测试<br>
参考 QueueApplicationTests 类<br>

##部署SpringBoot应用服务<br>


##拓展<br>
修改application.yml无需重启工程，支持热部署<br>

SpringBoot 指定访问url接口后缀 如： \*.do或\*.action <br>
只需要显示的创建一个ServletRegistrationBean 即可。
具体请参考：https://blog.csdn.net/alan_liuyue/article/details/88724616 <br>



