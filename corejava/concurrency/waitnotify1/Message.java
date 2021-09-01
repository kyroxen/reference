package corejava.concurrency.waitnotify1;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Message {
    private Integer id;
    private String threadName;
    private String payload;

    public Message(Integer id, String threadName, String payload) {
        this.id = id;
        this.threadName = threadName;
        this.payload = payload;
    }
}
