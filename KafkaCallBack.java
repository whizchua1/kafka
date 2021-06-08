import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaCallBack implements Callback {

    String messageKey;

    public KafkaCallBack(String messageKey) {
        super();
        this.messageKey=messageKey;
    }
    @Override
    public void onCompletion(RecordMetadata retData, Exception e) {

        if (e != null) {
            System.out.println("Exception without Callback :"
                    +"Message Key = " + messageKey + " : " + e.getMessage());
        }
        else {
            System.out.println(" Callback received for Message Key " + messageKey
                    + " returned Partition : " + retData.partition()
                    + " and Offset : " + retData.offset());
        }
    }
}
