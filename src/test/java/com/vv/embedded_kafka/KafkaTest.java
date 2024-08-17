package com.vv.embedded_kafka;

import com.vv.embedded_kafka.consumer.KafkaConsumer;
import com.vv.embedded_kafka.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@EmbeddedKafka( partitions = 1,
                topics = { "test-topic" },
                brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" } )
public class KafkaTest {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private KafkaConsumer consumer;

    @Test
    public void testKafka() throws InterruptedException {
        producer.sendMessage( "test-topic", "Hello, Kafka!" );
        consumer.getLatch()
                .await();
        assertThat( consumer.getLatch()
                            .getCount() ).isEqualTo( 0 );
    }
}

