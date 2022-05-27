package fr.radi3nt.updater.process.git;

import fr.radi3nt.json.Json;
import fr.radi3nt.json.JsonValue;
import fr.radi3nt.updater.components.basic.BasicRelease;
import fr.radi3nt.updater.components.git.GitRelease;
import fr.radi3nt.updater.process.UpdateFetcher;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GitFetcher implements UpdateFetcher<GitRelease> {

    private final String user;
    private final String repo;

    private GitRelease[] gitReleases;

    public GitFetcher(String user, String repo) {
        this.user = user;
        this.repo = repo;
    }

    @Override
    public void fetch() throws IOException {
        JsonValue jsonValue = getJsonValue();

        List<GitRelease> gitReleases = new ArrayList<>();

        for (JsonValue value : jsonValue.asArray().values()) {
            GitRelease gitRelease = GitRelease.from(value.asObject());
            gitReleases.add(gitRelease);
        }

        gitReleases.removeIf(gitRelease -> gitRelease.getPublishedAt()==null || gitRelease.isDraft());
        gitReleases.sort(Comparator.comparing(BasicRelease::getPublishedAt));
        Collections.reverse(gitReleases);

        this.gitReleases = gitReleases.toArray(new GitRelease[0]);
    }

    private JsonValue getJsonValue() throws IOException {
        URL url = new URL("https://api.github.com/repos/" + user + "/" + repo + "/releases");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int code = connection.getResponseCode();
        if (code==200) {
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            JsonValue jsonValue = Json.parse(inputStreamReader);

            inputStreamReader.close();
            return jsonValue;
        } else {
            throw new ConnectionCodeException(code);
        }
    }

    @Override
    public GitRelease[] getReleases() {
        return gitReleases;
    }
}
