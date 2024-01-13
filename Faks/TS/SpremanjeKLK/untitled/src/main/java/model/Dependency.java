package model;

import java.util.ArrayList;
import java.util.List;

public class Dependency extends MavenProject{

    private boolean optional;
    private List<ProjectIdentifier> excludedTransitive;
    private String scope; // test, runtime, compile, provided

    public Dependency(String groupId, String artefactId, int versionMajor, int versionMinor, int versionPatch) {
        super(groupId, artefactId, versionMajor, versionMinor, versionPatch);
        this.scope = "compile";
        this.optional = false;
        this.excludedTransitive = new ArrayList<>();
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public List<ProjectIdentifier> getExcludedTransitive() {
        return excludedTransitive;
    }

    public void setExcludedTransitive(List<ProjectIdentifier> excludedTransitive) {
        this.excludedTransitive = excludedTransitive;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
