package com.country.queue;

import org.joda.money.Money;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
* Description:SpringRunner 继承了SpringJUnit4ClassRunner，没有扩展任何功能；
 * 关于webEnvironment，有MOCK、RANDOM_PORT、DEFINED_PORT、NONE四个选项，其中：
 *
 * MOCK —提供一个虚拟的Servlet环境，内置的Servlet容器并没有真实的启动
 * RANDOM_PORT — 提供一个真实的Servlet环境，也就是说会启动内置容器，然后使用的是随机端口
 * DEFINED_PORT — 这个配置也是提供一个真实的Servlet环境，使用的配置文件中配置的端口，如果没有配置，默认是8080
 * 使用前者，名字简短而已。
 *
 *当不需要模拟真实的servlet容器时，我们一般选用 MOCK
* @author: yuhang tao
* @date: 2020/4/18
* @version: v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QueueApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QueueApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     * 获取容器开启的端口号
     */
    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity(
                "http://127.0.0.1:" + port + "/boot/home", String.class, "");
        System.out.println(entity.getBody());
    }

    @Test
    public void testMoney(){
        Money money1=Money.parse("CNY 10000000000000000020020");
        Money money2=Money.parse("CNY 1000000000000000");
        System.out.println(money1.multipliedBy(money2.getAmountMajorLong()).getAmount().toString());
    }

}
