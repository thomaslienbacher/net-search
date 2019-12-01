package swp.netsearch.restapi;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "port_connections")
public class AssignedConnection {
    int id_port_connections;
    int _switch; //TODO: rename because reserved keyword
    int room;
    int port;

    public AssignedConnection() {
    }

    @Id
    @Column(name = "id_port_connections")//TODO: rename to id_port_connection
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_port_connections() {
        return id_port_connections;
    }

    public void setId_port_connections(int id_port_connections) {
        this.id_port_connections = id_port_connections;
    }

    @Column(name = "switch")
    public int get_switch() {
        return _switch;
    }

    @Column(name = "switch")
    public void set_switch(int _switch) {
        this._switch = _switch;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
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
                "id_port_connections=" + id_port_connections +
                ", _switch=" + _switch +
                ", room=" + room +
                ", port=" + port +
                '}';
    }
}
