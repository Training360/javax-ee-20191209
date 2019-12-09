package empapp;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NameTrimmer {

    public String trimName(String name) {
        return name.trim();
    }
}
