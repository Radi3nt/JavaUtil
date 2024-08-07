package fr.radi3nt.file.impl;

import fr.radi3nt.file.files.ReadableFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ResourceFile implements ReadableFile {

	private static final String FILE_SEPARATOR = "/";
	private static final String BASE_PATH = "";

	private final String path;
	private final String name;

	public ResourceFile(String path) {
		this.path = path;
		String[] dirs = path.split(FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}

	public ResourceFile(String... paths) {
		String path = BASE_PATH;
		for (final String part : paths) {
			path += FILE_SEPARATOR + part;
		}
		this.path = path;
		final String[] dirs = this.path.split(FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}

	public ResourceFile(ResourceFile file, String subFile) {
		this.path = file.path + FILE_SEPARATOR + subFile;
		this.name = subFile;
	}

	public ResourceFile(ResourceFile file, String... subFiles) {
		String path = file.path;
		for (String part : subFiles) {
			path += (FILE_SEPARATOR + part);
		}
		this.path = path;
		String[] dirs = path.split(FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return getPath();
	}

	public InputStream getInputStream() {
		InputStream stream = ResourceFile.class.getResourceAsStream(path);
		if (stream==null)
			throw new IllegalStateException("Could not get resource '" + path + "', as it doesn't seem to exist");
		return stream;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isCreated() {
		InputStream inputStream = getInputStream();

		boolean exists = inputStream!=null;

		if (exists) {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return exists;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResourceFile)) return false;

		ResourceFile that = (ResourceFile) o;
		return Objects.equals(path, that.path) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(path);
		result = 31 * result + Objects.hashCode(name);
		return result;
	}
}
