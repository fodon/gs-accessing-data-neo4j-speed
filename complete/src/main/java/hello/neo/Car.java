/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.neo;

import java.util.Random;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 *
 * @author fodon
 */
@NodeEntity
public class Car {

    @GraphId
    private Long id;
    private String type;

    private Car() {

    }

    public Car(Random rand) {
        this.type = Long.toHexString(rand.nextLong());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
