package webservice.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import webservice.jsonUtil.ReadJson;
import webservice.models.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupManager {
    @Value("${URL}")
    private String urlAllGroups;

    @Autowired
    private ReadJson reader;

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        JSONObject json = reader.readJsonFromUrl(urlAllGroups);
        JSONArray groupsJson = (JSONArray) json.get("data");
        for (int i = 0; i < groupsJson.length(); i++) {
            Group group = new Group();
            JSONObject groupJson = (JSONObject) groupsJson.get(i);
            group.setId((int) groupJson.get("group_id"));
            group.setFullName((String) groupJson.get("group_full_name"));
            group.setUrl((String) groupJson.get("group_url"));
            group.setGroupType((String) groupJson.get("group_type"));
            groups.add(group);
        }
        return groups;
    }

    public List<String> getGroupsName() {
        List<String> list = new ArrayList<>();
        getAllGroups().stream().forEach(group -> list.add(group.getFullName()));
        return list;
    }

    public Group getGroup(String groupName) {
        return getAllGroups().stream().filter(group -> group.getFullName().equals(groupName)).findAny().orElse(null);
    }
}
