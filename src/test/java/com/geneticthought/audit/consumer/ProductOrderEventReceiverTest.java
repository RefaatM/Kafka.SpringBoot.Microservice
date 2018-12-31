package com.geneticthought.audit.consumer;

import com.geneticthought.audit.service.ProductOrderEventProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductOrderEventReceiverTest {

    @Mock private ProductOrderEventProcessor processor;
    @Mock private ConsumerRecord consumerRecord;

    @InjectMocks private ProductOrderEventReceiver receiver;

    @Test
    public void invokesProcessorWithTransformedEvent() throws IOException {
        given(consumerRecord.value()).willReturn(productOrderEvent());

        receiver.receive(consumerRecord);

        verify(processor).processEvent(isA(ProductOrderEvent.class));
    }

    private String productOrderEvent() {
        return "{" +
                "\"userLogin\":\"someLogin\"," +
                "\"productId\":\"someId\"," +
                "\"creationTime\":\"2018-06-20T22:51:20.030241\"" +
                "}";
    }

}