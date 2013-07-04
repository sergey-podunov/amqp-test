package org.amqptest;

public enum ProtocolVersion {
    VER_0_9_1(0, 9, 1);
    private int major;
    private int minor;
    private int revision;

    private ProtocolVersion(int major, int minor, int revision) {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getRevision() {
        return revision;
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + revision;
    }

    public boolean equalsByParts(int major, int minor, int revision) {
        return this.major == major
                && this.minor == minor
                && this.revision == revision;
    }
}
