package bucolicasfilos.example.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

/**
 * Normaliza o DATABASE_URL do Render para o formato JDBC esperado pelo Spring
 * Boot.
 * O Render fornece a URL nos formatos:
 * postgres://user:pass@host:port/db (sem jdbc:, com alias "postgres")
 * postgresql://user:pass@host:port/db (sem jdbc:)
 * jdbc:postgresql://user:pass@host:port/db (já correto)
 */
public class DatabaseUrlPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String rawUrl = System.getenv("DATABASE_URL");
        if (rawUrl == null || rawUrl.isBlank()) {
            return;
        }

        String jdbcUrl;
        if (rawUrl.startsWith("jdbc:")) {
            jdbcUrl = rawUrl; // já está no formato correto
        } else if (rawUrl.startsWith("postgres://")) {
            jdbcUrl = "jdbc:postgresql://" + rawUrl.substring("postgres://".length());
        } else if (rawUrl.startsWith("postgresql://")) {
            jdbcUrl = "jdbc:" + rawUrl;
        } else {
            jdbcUrl = rawUrl; // formato desconhecido, mantém como está
        }

        // Render exige sslmode=require; adiciona se ainda não estiver presente
        if (!jdbcUrl.contains("sslmode") && !jdbcUrl.contains("ssl=")) {
            jdbcUrl += (jdbcUrl.contains("?") ? "&" : "?") + "sslmode=require";
        }

        environment.getPropertySources().addFirst(
                new MapPropertySource("databaseUrlNormalized", Map.of(
                        "spring.datasource.url", jdbcUrl,
                        "DATABASE_URL", jdbcUrl)));
    }
}
