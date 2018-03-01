/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author dobszai
 */
public interface Edge {

    public String getId();
    
    public Vertex getDestination();

    public Vertex getSource();
    public int getWeight();

    @Override
    public String toString();
}
