package net.kender.version;

public class Version {
    private int Major = 0;
    private int Minor = 0;

    public Version(String version) {
        int index = version.indexOf(".");
        int major = Integer.parseInt(version.substring(index - 1, index));
        int minor = Integer.parseInt(version.substring(index + 1, index + 2));

        Major = major;
        Minor = minor;
    }

    public boolean CompareInt(int MajorToCompare, int minorToCompare) {
        if (this.Major == MajorToCompare || this.Minor == minorToCompare) {
            return true;
        } else {
            return false;
        }
    }

    public boolean CompareString(String versionToCompare) {
        int index = versionToCompare.indexOf(".");
        int major = Integer.parseInt(versionToCompare.substring(index - 1, index));
        int minor = Integer.parseInt(versionToCompare.substring(index + 1, index + 2));

        if (this.Major == major || this.Minor == minor) {
            return true;
        } else {
            return false;
        }
    }

    public int getMajor() {
        return Major;
    }

    public int getMinor() {
        return Minor;
    }

}
