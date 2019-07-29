package runnable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

import codec.MessageCodec;
import logger.LoggerFactory;

public class UDPSocketSendRunnable extends SendRunnable {
    private DatagramSocket mSocket;
    private DatagramPacket packet;
    public UDPSocketSendRunnable(MessageCodec codec, DatagramSocket socket) {
        super(codec);
        this.mSocket = socket;
    }

    @Override
    protected boolean doSend(SocketAddress remoteAddress, byte[] buffer, int offset, int length) {
        if (mSocket != null) {
            try {
                if (packet == null) {
                    packet = new DatagramPacket(buffer, buffer.length);
                }
                packet.setData(buffer, offset, length);
                packet.setSocketAddress(remoteAddress);
                mSocket.send(packet);
                return true;
            } catch (IOException e) {
                LoggerFactory.getLogger().error("数据发送异常！", e);
            }
        } else {
            LoggerFactory.getLogger().error("发送管道DatagramSocket为NULL！");
        }
        return false;
    }
}
