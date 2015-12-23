package work;


import java.io.Serializable;
import java.net.InetSocketAddress;

public class Location implements Serializable {
    private InetSocketAddress location;
    public int count;

    public Location() {
    }

    public Location(int g) {
        count = g;
    }


    public Location(InetSocketAddress location) {
        this.location = location;
    }

    public Location(InetSocketAddress location,int c) {
        this.location = location;
        count = c;
    }

    public Location(String ipAddress, int port) {
        location = new InetSocketAddress(ipAddress, port);
    }


    public InetSocketAddress getLocation() {
        return location;
    }

    public int getCount(){

        return count;
    }


    public void setLocation(InetSocketAddress location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Location{" +
                "ip address=" + location.getHostString() + ", " +
                "port=" + location.getPort() + ", number of connections=" + count+
                '}';
    }
}
