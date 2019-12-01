package swp.netsearch.restapi;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "rooms")
public class Room {
    int id_rooms;
    String name;

    public Room() {}

    @Id
    @Column(name = "id_rooms")//TODO: rename to id_room
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_rooms() {
        return id_rooms;
    }

    public void setId_rooms(int id_rooms) {
        this.id_rooms = id_rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id_rooms=" + id_rooms +
                ", name='" + name + '\'' +
                '}';
    }
}
