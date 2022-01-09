/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;


/**
 *
 * @author bryan
 */
public class Pictures {
    int id;
    String Name;

    public Pictures(int id, String Name) {
        this.id = id;
        this.Name = Name;
    }

    public Pictures() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    

    @Override
    public String toString() {
        return  id + ": " + Name;
    }
    
    
}
