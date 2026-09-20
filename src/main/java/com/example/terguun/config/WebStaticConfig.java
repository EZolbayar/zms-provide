package com.example.terguun.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Next.js статик export-оор гарсан frontend-ийг (classpath:/static) хүргэнэ. Export нь /customers
 * гэсэн замд customers.html файл үүсгэдэг тул .html өргөтгөлгүй хаягийг мөн танина.
 */
@Configuration
public class WebStaticConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource direct = location.createRelative(resourcePath);
                        if (direct.exists() && direct.isReadable() && !resourcePath.isEmpty()
                                && !resourcePath.endsWith("/")) {
                            return direct;
                        }
                        String base = resourcePath.isEmpty() || resourcePath.endsWith("/")
                                ? resourcePath + "index"
                                : resourcePath;
                        Resource html = location.createRelative(base + ".html");
                        return html.exists() && html.isReadable() ? html : null;
                    }
                });
    }
}
