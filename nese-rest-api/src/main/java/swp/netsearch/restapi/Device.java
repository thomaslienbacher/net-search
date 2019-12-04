package swp.netsearch.restapi;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "devices")
public class Device {
    int id_device;
    String name;
    String mac;

    public Device() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_device() {
        return id_device;
    }

    public void setId_device(int id_device) {
        this.id_device = id_device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id_device=" + id_device +
                ", name='" + name + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
