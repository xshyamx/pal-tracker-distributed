package io.pivotal.pal.tracker.allocations;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestOperations;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectClient {

    private final RestOperations restOperations;
    private final String registrationServerEndpoint;

    private Map<Long,ProjectInfo> hashMap = new ConcurrentHashMap<>();

    public ProjectClient(RestOperations restOperations, String registrationServerEndpoint) {
        this.restOperations= restOperations;
        this.registrationServerEndpoint = registrationServerEndpoint;
    }



    @HystrixCommand(fallbackMethod = "getProjectFromCache")
    public ProjectInfo getProject(long projectId) {
        ProjectInfo  pi =  restOperations.getForObject(registrationServerEndpoint + "/projects/" + projectId, ProjectInfo.class);
        hashMap.put(projectId,pi);
        return pi;
    }

    public ProjectInfo getProjectFromCache(long projectId){
        return hashMap.get(projectId);
    }
}
