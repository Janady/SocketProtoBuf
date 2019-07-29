package runnable;

import java.io.OutputStream;
import java.net.SocketAddress;

import codec.MessageCodec;
import logger.LoggerFactory;

public class TCPSocketSendRunnable extends SendRunnable {
    private OutputStream mOutputStream;

    public TCPSocketSendRunnable(MessageCodec codec, OutputStream out) {
        super(codec);
        this.mOutputStream = out;
    }

    @Override
    protected boolean doSend(SocketAddress remoteAddress, byte[] buffer, int offset, int length) {
        if (mOutputStream != null) {
            try {
                mOutputStream.write(buffer, offset, length);
                mOutputStream.flush();
                return true;
            } catch (Exception e) {
                LoggerFactory.getLogger().error("数据发送异常！", e);
            }
        } else {
            LoggerFactory.getLogger().error("发送管道OutputStream为NULL！");
        }
        return false;
    }
}
