package com.tomato.base.loader;

import com.tomato.base.component.Component;
import com.tomato.base.component.ComponentManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by wangronghua on 15/8/2.
 */
public class ComponentLoader {


    public void load() {
        Components components = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Components.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            components = (Components) unmarshaller.unmarshal(this.getClass().getResourceAsStream("/components.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if(null != components) {
            for(String componentStr : components.getComponent()) {
                try {
                    Class clazz = Class.forName(componentStr);
                    Object instance = clazz.newInstance();
                    if(instance instanceof Component) {
                        ComponentManager.get().addComponent((Component) instance);
                    } else {
                        //ignore this class
                        //TODO log
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    //TODO log
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

@XmlRootElement
class Components {
    private List<String> component;

    @XmlElement
    public List<String> getComponent() {
        return component;
    }

    public void setComponent(List<String> component) {
        this.component = component;
    }

}
