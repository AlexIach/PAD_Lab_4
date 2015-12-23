package Discovery;


import work.Location;
import work.ProtocolConfig;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static work.SerializationUtils.deserialize;
import static work.SerializationUtils.serialize;


public class DiscoveryListener extends Thread {
    Location dataServerAddress;

    public DiscoveryListener(Location dataServerAddress) {
        this.dataServerAddress = dataServerAddress;
    }

    @Override
    public void run() {
        sendDataServerLocation(
                receiveClientRequest(),
                dataServerAddress);
    }

    /**
     * Receives request through UDP multicast and returns client location where
     * "discovery" listener must send information (address and port) about data
     * transport server.
     */
    private Location receiveClientRequest() {
        Location clientLocation = null;
        try {
            MulticastSocket s = new MulticastSocket(ProtocolConfig.PROTOCOL_GROUP_PORT);
            s.joinGroup(InetAddress.getByName(ProtocolConfig.PROTOCOL_GROUP_ADDRESS));

            byte buf[] = new byte[2048];
            DatagramPacket pingPacket = new DatagramPacket(buf, buf.length);
            s.receive(pingPacket);

            clientLocation = (Location) deserialize(pingPacket.getData());

            System.out.println("[INFO] -----------------------------------------\n" +
                    "[INFO] Received location request...");

            s.leaveGroup(InetAddress.getByName(ProtocolConfig.PROTOCOL_GROUP_ADDRESS));
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientLocation;
    }

    /**
     * Sends data server location using UDP unicast
     *
     * @param clientLocation where information is send
     * @param serverLocation where client app can request data collection
     */
    private void sendDataServerLocation(Location clientLocation, Location serverLocation) {
        try {
            byte[] sendDataServerAddress = serialize(serverLocation);
            DatagramSocket clientSocket = new DatagramSocket();

            DatagramPacket pongPacket = new DatagramPacket(sendDataServerAddress,
                    sendDataServerAddress.length,
                    clientLocation.getLocation());
            clientSocket.send(pongPacket);

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

