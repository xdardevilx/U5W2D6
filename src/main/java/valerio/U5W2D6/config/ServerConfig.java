package valerio.U5W2D6.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {

    @Bean
    public Cloudinary uploaderImg(@Value("${cloudinary.cloudName}") String cloudName,
                                  @Value("${cloudinary.apiKey}") String apiKey,
                                  @Value("${cloudinary.apiSecret}") String apiSecret) {
        Map<String, String> confing = new HashMap<>();
        confing.put("cloud_name", cloudName);
        confing.put("api_key", apiKey);
        confing.put("api_secret", apiSecret);
        return new Cloudinary(confing);


    }
}
