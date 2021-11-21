package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class app {
    public static void main(String[] args){
        SpringApplication.run(app.class,args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("zoos", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/clubs/{name}", "/api/clubs")
                        .uri("http://localhost:8081"))
                .route("animals", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/players", "/api/players/**", "/api/clubs/{name}/players", "/api/clubs/{name}/players/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
