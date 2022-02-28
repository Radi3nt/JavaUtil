package fr.radi3nt.json;

public class CharLocation {
    public final int offset;
    public final int line;
    public final int column;

    CharLocation(final int offset, final int line, final int column) {
        this.offset = offset;
        this.column = column;
        this.line = line;
    }

    @Override
    public String toString() {
        return this.line + ":" + this.column;
    }

    @Override
    public int hashCode() {
        return this.offset;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final CharLocation other = (CharLocation) obj;
        return this.offset == other.offset && this.column == other.column && this.line == other.line;
    }
}
