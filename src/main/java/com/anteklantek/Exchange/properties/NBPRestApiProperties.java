package com.anteklantek.Exchange.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("nbp-rest-api")
public class NBPRestApiProperties {


    private String aTableRestApiUrl;

}
