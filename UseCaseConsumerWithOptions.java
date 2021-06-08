import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class UseCaseConsumerWithOptions {

    public static void main(String[] args) {

        Properties kafkaProps = new Properties();

        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092,localhost:9093,localhost:9094");

        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG,
                "kafka-java-consumer");

        kafkaProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest");

        kafkaProps.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 20);

        kafkaProps.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 200);

        kafkaProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        KafkaConsumer<String, String> usecaseConsumer =
                new KafkaConsumer<String, String>(kafkaProps);

        usecaseConsumer.subscribe(Arrays.asList("kafka.usecase.students"));

        while(true) {

            ConsumerRecords<String, String> messages =
                    usecaseConsumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> message : messages) {
                System.out.println("Message fetched : " + message);
            }

            usecaseConsumer.commitAsync();
        }


    }
}
