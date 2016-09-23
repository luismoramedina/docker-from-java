package com.github.luismoramedina;

import io.fabric8.maven.docker.access.hc.DockerAccessWithHcClient;
import io.fabric8.maven.docker.model.Container;
import io.fabric8.maven.docker.util.EnvUtil;
import io.fabric8.maven.docker.util.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @author luismoramedina
 */
public class DockerClientMain {

    public static void main(String[] args) throws Exception {

        DockerAccessWithHcClient client = getClient();
        List<Container> containers = client.listContainers(1000);
        for (Container container : containers) {
            String image = container.getImage();
            String name = container.getName();
            System.out.println(image + " <==> " + name);
        }

    }

    private static DockerAccessWithHcClient getClient() throws IOException {
        String baseUrl = "unix:///var/run/docker.sock";
        return new DockerAccessWithHcClient("", baseUrl, EnvUtil.getCertPath(null), 20, null);
    }
}
