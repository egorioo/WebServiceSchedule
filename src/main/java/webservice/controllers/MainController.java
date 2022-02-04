package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webservice.models.Group;
import webservice.service.GroupManager;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class MainController {
    private final GroupManager groupManager;

    @Autowired
    public MainController(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @GetMapping
    public List<String> getAllGroups() {
        return groupManager.getGroupsName();
    }

    @GetMapping("/{groupName}")
    public Group getGroup(@PathVariable String groupName) {
        return groupManager.getGroup(groupName);
    }
}
