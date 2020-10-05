package db.entity;

public class Topic extends Entity {
    private static final long serialVersionUID = 2386302708905518585L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Category [name=" + name + ", getId()=" + getId() + "]";
    }

}
