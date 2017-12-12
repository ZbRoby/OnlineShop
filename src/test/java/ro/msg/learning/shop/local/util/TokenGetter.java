package ro.msg.learning.shop.local.util;

import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class TokenGetter {

    private final TestRestTemplate testRestTemplate;

    public TokenGetter(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    public String getAdminToken() {
        return getUserToken(testRestTemplate, "admin");
    }

    public String getCustomerToken() {
        return getUserToken(testRestTemplate, "customer");
    }

    private String getUserToken(TestRestTemplate testRestTemplate, String user) {
        String token = testRestTemplate.withBasicAuth("android", "123456")
            .postForObject("/oauth/token?" +
                "grant_type=" + "password" +
                "&username=" + user +
                "&password=" + "test" +
                "", null, String.class);
        return token.split("\"")[3];
    }
}
