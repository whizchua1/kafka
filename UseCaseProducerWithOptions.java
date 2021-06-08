import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.record.CompressionType;

import java.util.Properties;
import java.util.Random;

public class UseCaseProducerWithOptions {

    public static void main(String[] args) {

        Properties kafkaProps = new Properties();

        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092,localhost:9093,localhost:9094");

        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "all");

        kafkaProps.put(ProducerConfig.BATCH_SIZE_CONFIG,32768);

        KafkaProducer usecaseProducer = new KafkaProducer(kafkaProps);

        Random randomKey = new Random();

        for(int i=0; i < 10; i++) {

            String messageKey = String.valueOf(randomKey.nextInt(1000));

            ProducerRecord<String, String> asyncRecCallBack =
                    new ProducerRecord<String, String>(
                            "Producer",    //Topic name
                            messageKey,
                            "Producer " + messageKey
                    );

            usecaseProducer.send(asyncRecCallBack, new UseCaseCallBack(messageKey));

            System.out.println("\nSent Asynchronously with Callback :" + asyncRecCallBack);
        }

        usecaseProducer.close();
    }
}


