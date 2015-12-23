package Discovery;


import work.Location;
import work.ProtocolConfig;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.SECONDS;
import static work.SerializationUtils.deserialize;
import static work.SerializationUtils.serialize;


public class DiscoveryClient {

    private Location clientAddress;

    /**
     * @param clientAddress client location where discovery servers send data locations
     */
    public DiscoveryClient(Location clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Location retrieveLocation() throws IOException {
        ArrayList<Location> locations = null;
        Optional<Location> locations2 = null;

        sendLocationRequest();
        locations = receiveLocations();

        if (locations.size() > 0) {
            locations2 = locations.stream()
                    .max(Comparator.comparing(Location::getCount));




            return locations2.get();



        } else {
            return null;
        }
    }

    /**
     * Receives UDP unicast datagrams which include
     * server locations of distributed data collections
     *
     * @throws IOException
     */
    private ArrayList<Location> receiveLocations() throws IOException {

        ArrayList<Location> locations = new ArrayList<Location>();
        DatagramSocket datagramServer = new DatagramSocket(clientAddress.getLocation());
        byte dataFromServer[] = new byte[2048];
        boolean isTimeExpired = false;
        datagramServer.setSoTimeout((int) SECONDS.toMillis(ProtocolConfig.PROTOCOL_GROUP_TIMEOUT));

        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Discovering... information nodes.");
        while (!isTimeExpired) {
            DatagramPacket pongPacket = new DatagramPacket(dataFromServer, dataFromServer.length);
            try {
                datagramServer.receive(pongPacket);
            } catch (SocketTimeoutException e) {
                System.out.println("[WARNING] -----------------------------------------\n" +
                        "[WARNING] Waiting time expired...");
                isTimeExpired = true;
                continue;
            }
            locations.add((Location) deserialize(pongPacket.getData()));
            System.out.println("[INFO] " +
                    "Receiving reply from: (" +
                    pongPacket.getPort() + ", " +
                    pongPacket.getAddress().getHostAddress() + ")");
        }
        datagramServer.close();
        return locations;
    }

    /**
     * Sends UDP multicast request to node group of distributed system.
     * Request includes client address used by discovery listener.
     *
     * @throws IOException
     */

    private void sendLocationRequest() throws IOException {
        MulticastSocket s = new MulticastSocket();
        byte sendData[] = serialize(new Location(clientAddress.getLocation()));
        DatagramPacket pingPacket = new DatagramPacket(sendData, sendData.length,
                InetAddress.getByName(ProtocolConfig.PROTOCOL_GROUP_ADDRESS),
                ProtocolConfig.PROTOCOL_GROUP_PORT);
        s.send(pingPacket);
        s.close();
    }


}
