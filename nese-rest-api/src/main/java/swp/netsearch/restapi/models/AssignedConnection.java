package swp.netsearch.restapi.models;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "port_connections")
public class AssignedConnection {
    int id_port_connection;
    int switch_id;
    int room_id;
    int port;

    public AssignedConnection() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_port_connection() {
        return id_port_connection;
    }

    public void setId_port_connection(int id_port_connection) {
        this.id_port_connection = id_port_connection;
    }

    public int getSwitch_id() {
        return switch_id;
    }

    public void setSwitch_id(int switch_id) {
        this.switch_id = switch_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room) {
        this.room_id = room;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "AssignedConnection{" +
                "id_port_connection=" + id_port_connection +
                ", switch_id=" + switch_id +
                ", room_id=" + room_id +
                ", port=" + port +
                '}';
    }
}
