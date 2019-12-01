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
    int id_devices;
    String name;
    String mac;

    public Device() {}

    @Id
    @Column(name = "id_devices")//TODO: rename to id_device
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_devices() {
        return id_devices;
    }

    public void setId_devices(int id_devices) {
        this.id_devices = id_devices;
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
                "id_devices=" + id_devices +
                ", name='" + name + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
