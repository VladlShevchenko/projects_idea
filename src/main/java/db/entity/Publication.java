package db.entity;

import com.mysql.cj.jdbc.Blob;
public class Publication extends Entity {
    private static final long serialVersionUID = 4716395168539434663L;

    private String name;

    private float priceForMonth;

    private String image;
    private String description;

    private Long topicId;

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceForMonth(Integer priceForMonth) {
        this.priceForMonth = priceForMonth;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public float getPriceForMonth() {
        return priceForMonth;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Long getTopicId() {
        return topicId;
    }
    @Override
    public String toString() {
        return "Publication [name=" + name + ", price for month=" + priceForMonth + ", imageBlob="
                + image + ", description=" + description +", topicId=" + topicId +
                ", getId()=" + getId() + "]";
    }

}
