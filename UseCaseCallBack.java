import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class UseCaseCallBack implements Callback {

    String messageKey;

    public UseCaseCallBack(String messageKey) {
        this.messageKey=messageKey;
    }
    @Override
    public void onCompletion(RecordMetadata retData, Exception e) {

        if (e != null) {
            System.out.println("Exception Publishing Asynchronously without Callback :"
                    + e.getMessage());
        }
        else {
            System.out.println(" Callback received for Message Key " + messageKey
                    + " returned Partition : " + retData.partition()
                    + " and Offset : " + retData.offset());
        }
    }
}
