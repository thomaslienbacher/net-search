package swp.netsearch.restapi.models;

import swp.netsearch.restapi.util.Utils;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "switches")
public class Switch {
    int id_switch;
    String name;
    String ip; //represented as 123.44.230.13
    String community_string;

    public Switch() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_switch() {
        return id_switch;
    }

    public void setId_switch(int id_switch) {
        this.id_switch = id_switch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIp() {
        return Utils.ipToInt(ip);
    }

    public void setIp(long ip) {
        this.ip = Utils.intToIp(ip);
    }

    public String getCommunity_string() {
        return community_string;
    }

    public void setCommunity_string(String community_string) {
        this.community_string = community_string;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "id_switch=" + id_switch +
                ", name='" + name + '\'' +
                ", ip=" + ip +
                ", community_string='" + community_string + '\'' +
                '}';
    }
}
