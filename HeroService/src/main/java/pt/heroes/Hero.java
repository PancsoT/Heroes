package pt.heroes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hero")
@Entity
@Table(name = "hero")
public class Hero {
    int id;
    String name;

    public Hero() {

    }

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}