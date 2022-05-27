package fr.radi3nt.updater.components.git;

import fr.radi3nt.json.JsonObject;
import fr.radi3nt.updater.api.Author;

import java.util.Objects;

public class GitAuthor implements Author {

    private final String name;
    private final int id;

    public GitAuthor(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static GitAuthor from(JsonObject uploader) {
        String name = uploader.getString("login");
        int id = uploader.getInt("id", -1);

        return new GitAuthor(name, id);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitAuthor)) return false;
        GitAuthor gitAuthor = (GitAuthor) o;
        return Objects.equals(getName(), gitAuthor.getName()) && Objects.equals(getId(), gitAuthor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}
