package dz8;

import dz8.product.Product;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Payloads;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

@SpringBootApplication
@IntegrationComponentScan
public class ServerApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(ServerApplication.class, args);
        MessageChannel messageChannel = context.getBean(DirectChannel.class);

        Product product = new Product(1, "NewProduct");

        Message<Product> productMessage = MessageBuilder
                .withPayload(product)
                .setHeader("X-TENANT", "Application-001N")
                .build();
        messageChannel.send(productMessage);
    }

    @ServiceActivator(inputChannel = "inChannelOne")
    public void inChannelOne(@Payload Product payload, @Headers Map<String, Object> headers){
        System.out.println("Payload: " + payload);
        System.out.println("Headers: ");
        headers.forEach((k, v) -> System.out.printf("%s: %s%n", k, v));
    }
}
