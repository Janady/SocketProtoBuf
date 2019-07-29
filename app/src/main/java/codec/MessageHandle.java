package codec;

public interface MessageHandle<T extends Message> {
    /**
     * status callback
     * @param status
     */
    void onStatus(int status);

    /**
     * Message callback
     * after decode, will call onReceive
     * do work on new thread.
     *
     * @param message
     */
    void onMessage(T message);
}
