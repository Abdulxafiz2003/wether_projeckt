package com.example.demoauth.configs;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.client.WebClient;
//
//
//@Configuration
//public class WebClientConfiguration {
//
//    //Simple configuration for web client
//    @Bean
//    public WebClient getWebClient ()
//    {
//        return WebClient.builder ()
//                .baseUrl ("http://localhost:8080")
//                .defaultHeader (HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build ();
//    }
//    Can be implemented as you like, depending on what it is used for
//    @Bean
//    public WebClient webClientWithTimeout() throws SSLException {
//        SslContext context = SslContextBuilder.forClient()
//                .trustManager(InsecureTrustManagerFactory.INSTANCE)
//                .build();
//        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
//
//        return WebClient.builder()
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//                    if (clientResponse.statusCode().isError()) {
//                        return clientResponse.bodyToMono(String.class)
//                                .flatMap(errorDetails -> Mono.error(new ExternalException(clientResponse.statusCode().value(), errorDetails)));
//                    }
//                    return Mono.just(clientResponse);
//                })).build();
//    }
//
//
//}
