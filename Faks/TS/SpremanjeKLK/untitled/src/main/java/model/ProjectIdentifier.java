package model;

import java.util.Objects;

public class ProjectIdentifier {

    private String groupId;
    private String artefactId;
    private int versionMajor, versionMinor, versionPatch;

    public ProjectIdentifier(String groupId, String artefactId, int versionMajor, int versionMinor, int versionPatch) {
        this.groupId = groupId;
        this.artefactId = artefactId;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.versionPatch = versionPatch;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtefactId() {
        return artefactId;
    }

    public void setArtefactId(String artefactId) {
        this.artefactId = artefactId;
    }

    public int getVersionMajor() {
        return versionMajor;
    }

    public void setVersionMajor(int versionMajor) {
        this.versionMajor = versionMajor;
    }

    public int getVersionMinor() {
        return versionMinor;
    }

    public void setVersionMinor(int versionMinor) {
        this.versionMinor = versionMinor;
    }

    public int getVersionPatch() {
        return versionPatch;
    }

    public void setVersionPatch(int versionPatch) {
        this.versionPatch = versionPatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectIdentifier that = (ProjectIdentifier) o;
        return versionMajor == that.versionMajor && versionMinor == that.versionMinor && versionPatch == that.versionPatch && Objects.equals(groupId, that.groupId) && Objects.equals(artefactId, that.artefactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, artefactId, versionMajor, versionMinor, versionPatch);
    }

    @Override
    public String toString() {
        return String.format("[groupId = %s, artefactId = %s, version = %d.%d.%d]",groupId,artefactId,versionMajor,versionMinor,versionPatch);
    }
}
