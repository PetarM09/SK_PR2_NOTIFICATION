package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MavenProject {

    private ProjectIdentifier identifier;
    private List<Dependency> dependencies;

    public MavenProject(String groupId, String artefactId, int versionMajor, int versionMinor, int versionPatch) {
        this.identifier = new ProjectIdentifier(groupId, artefactId, versionMajor, versionMinor, versionPatch);
        dependencies = new ArrayList<>();


    }

    public ProjectIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(ProjectIdentifier identifier) {
        this.identifier = identifier;
    }

    public String getGroupId() {
        return identifier.getGroupId();
    }

    public void setGroupId(String groupId) {
        identifier.setGroupId(groupId);
    }

    public String getArtefactId() {
        return identifier.getArtefactId();
    }

    public void setArtefactId(String artefactId) {
        identifier.setArtefactId(artefactId);
    }

    public int getVersionMajor() {
        return identifier.getVersionMajor();
    }

    public void setVersionMajor(int versionMajor) {
        identifier.setVersionMajor(versionMajor);
    }

    public int getVersionMinor() {
        return identifier.getVersionMinor();
    }

    public void setVersionMinor(int versionMinor) {
        identifier.setVersionMinor(versionMinor);
    }

    public int getVersionPatch() {
        return identifier.getVersionPatch();
    }

    public void setVersionPatch(int versionPatch) {
        identifier.setVersionPatch(versionPatch);
    }


    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public boolean idDirectDependency(String groupId, String artefactId, int major, int minor, int patch){
        for(Dependency dep:dependencies){
            if(dep.getGroupId().equals(groupId) && dep.getArtefactId().equals(artefactId)
                    && dep.getVersionMajor()==major && dep.getVersionMinor()==minor && dep.getVersionPatch()==patch)
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MavenProject that = (MavenProject) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    public boolean addDependency(Dependency dep){
        if(dependencies==null)
            dependencies = new ArrayList<>();
        return dependencies.add(dep);
    }
}
