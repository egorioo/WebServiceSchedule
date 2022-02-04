package webservice.models;

public class Group {
    private int id;
    private String fullName;
    private String url;
    private String groupType;

    public Group() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
