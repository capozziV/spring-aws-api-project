package com.capzz.spring_aws.producer;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.capzz.spring_aws.dto.EnvelopeDto;
import com.capzz.spring_aws.dto.ProductDto;
import com.capzz.spring_aws.enums.EventType;
import com.capzz.spring_aws.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    private static final Logger log = LoggerFactory.getLogger(ProductProducer.class);
    private AmazonSNS snsClient;
    private Topic productEventsTopic;
    private ObjectMapper objectMapper;

    public ProductProducer(AmazonSNS snsClient,
                           @Qualifier("productEventsTopic") Topic productEventsTopic,
                           ObjectMapper objectMapper){

        this.snsClient = snsClient;
        this.productEventsTopic = productEventsTopic;
        this.objectMapper = objectMapper;
    }

    public void publishProductEvent(Product product, EventType eventType, String username) throws JsonProcessingException {

        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getId());
        productDto.setCode(product.getCode());
        productDto.setUsername(username);

        EnvelopeDto envelopeDto = new EnvelopeDto();
        envelopeDto.setEventType(eventType);
        envelopeDto.setData(objectMapper.writeValueAsString(productDto));

        snsClient.publish(
                productEventsTopic.getTopicArn(),
                objectMapper.writeValueAsString(envelopeDto)
        );

    }

}
