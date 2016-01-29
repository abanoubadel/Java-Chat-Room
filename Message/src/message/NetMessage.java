package message;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author mahmoud_fouad
 */
public class NetMessage implements Serializable {

    public static enum NetMessageType {
        NONSTATE,
        LOGIN,
        DISCONNECT,
        MESSAGE,
        AUTH_OK,
        AUTH_FAIL,
        CONTACT_LIST,
        UPDATE_CONTACT_LIST,
        STATE_CHANGE,
        CREATE,
        VALIDATE_USER_NAME,
        VALIDATE_EMAIL,
        USER_NAME_VALIDATION_RESULT,
        EMAIL_VALIDATION_RESULT
    };

    private NetMessageType Type;
    private Object data;
    private Object attribute;
    private Vector<Integer> receiver;

    public NetMessage() {
        Type = NetMessageType.NONSTATE;
    }

    public NetMessage(NetMessageType state) {
        this.Type = state;
    }

    public NetMessage(NetMessageType state, Object data) {
        this.Type = state;
        this.data = data;
    }

    public NetMessage(NetMessageType Type, Object data, Object attribute) {
        this.Type = Type;
        this.data = data;
        this.attribute = attribute;
    }

    public NetMessage(NetMessageType Type, Object data, Vector<Integer> receiver) {
        this.Type = Type;
        this.data = data;
        if (receiver != null) {
            this.receiver = new Vector<Integer>(receiver);
        }
    }

    public NetMessage(NetMessageType Type, Object data, Object attribute, Vector<Integer> receiver) {
        this.Type = Type;
        this.data = data;
        this.attribute = attribute;
        this.receiver = receiver;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NetMessageType getType() {
        return Type;
    }

    public void setType(NetMessageType Type) {
        this.Type = Type;
    }

    public void setReceiver(Vector<Integer> receiver) {
        this.receiver = new Vector<Integer>(receiver);
    }

    public Vector<Integer> getReceiver() {
        return receiver;
    }

    public Object getAttribute() {
        return attribute;
    }

    public void setAttribute(Object attribute) {
        this.attribute = attribute;
    }

}
