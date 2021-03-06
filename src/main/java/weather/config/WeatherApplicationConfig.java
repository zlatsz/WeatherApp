package weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherApplicationConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

//    @Value("${split.io.api.key}")
//    private String splitApiKey;
//
//    @Bean
//    public SplitClient splitClient() throws Exception {
//        SplitClientConfig config = SplitClientConfig.builder()
//                .setBlockUntilReadyTimeout(10000)
//                .enableDebug()
//                .build();
//
//        SplitFactory splitFactory = SplitFactoryBuilder.build(splitApiKey, config);
//        SplitClient client = splitFactory.client();
//        client.blockUntilReady();
//
//        return client;
//    }
}
