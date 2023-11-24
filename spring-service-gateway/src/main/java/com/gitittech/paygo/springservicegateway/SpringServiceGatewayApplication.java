package com.gitittech.paygo.springservicegateway;

import com.gitittech.paygo.auth.PermissionSeeder;
import com.gitittech.paygo.commons.seeders.SeederExecutor;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.scheduling.annotation.EnableAsync;


@ComponentScans({
    @ComponentScan("com.gitittech.paygo.beneficiary"),
    @ComponentScan("com.gitittech.paygo.message"),
    @ComponentScan("com.gitittech.paygo.transaction"),
    @ComponentScan("com.gitittech.paygo.paymentmethod"),
    @ComponentScan("com.gitittech.paygo.settings"),
    @ComponentScan("com.gitittech.paygo.user"),
    @ComponentScan("com.gitittech.paygo.auth"),
    @ComponentScan("com.gitittech.paygo.commons"),
    @ComponentScan("com.gitittech.paygo.springservicegateway")
})
@EnableTransactionManagement
@EntityScan(basePackages = {"com.gitittech.paygo.entities"})
@EnableJpaRepositories(basePackages = {
    "com.gitittech.paygo.settings",
    "com.gitittech.paygo.user",
    "com.gitittech.paygo.auth",
    "com.gitittech.paygo.beneficiary",
    "com.gitittech.paygo.message",
    "com.gitittech.paygo.transaction",
    "com.gitittech.paygo.paymentmethod"
})
@EnableAsync
@SpringBootApplication
public class SpringServiceGatewayApplication {

    @Value("${custom.general.baseUrl}")
    private String baseUrl;

    public static void main(String[] args) {
        SpringApplication.run(SpringServiceGatewayApplication.class, args);
        boolean runSeed = Arrays.stream(args)
                .filter(item -> item.equals("seed"))
                .count() > 0;
        if (runSeed) {
            final var seederExecutor = new SeederExecutor(Set.of(PermissionSeeder.class));
            seederExecutor.execute();
        }
    }

    @Bean
    public OpenAPI openApiInformation() {       

        Contact contact = new Contact()
                .email("ambagape@gmail.com")
                .name("Ambrose Ariagiegbe");

        Info info = new Info()
                .contact(contact)
                .description("GititDocs Financials API")
                .title("GititDocs Financials API")
                .version("V3.0.0");

        return new OpenAPI().info(info)
                .addServersItem(new Server()
                        .url("https://paygo.staging.gitit-tech.com:9000")
                        .description("Staging Server URL")
                ).addServersItem(new Server()
                        .url("https://localhost:9000")
                        .description("Localhost Server URL")
                ).addServersItem(new Server()
                        .url("https://13.53.133.58:9000")
                        .description("IP Based Server URL")
                );
    }
}
