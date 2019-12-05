package swp.netsearch.restapi.models;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "rooms")
public class Room {
    int id_room;
    String name;

    public Room() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
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
                "id_room=" + id_room +
                ", name='" + name + '\'' +
                '}';
    }
}
