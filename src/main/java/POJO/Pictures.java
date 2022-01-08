/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.net.URL;

/**
 *
 * @author bryan
 */
public class Pictures {
    int id;
    URL url;
    String Name;

    public Pictures(int id, URL url, String Name) {
        this.id = id;
        this.url = url;
        this.Name = Name;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    

    @Override
    public String toString() {
        return  "Picture Id:" + id;
    }
    
    
}
