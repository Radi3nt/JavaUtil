package fr.radi3nt.file.impl;

import fr.radi3nt.file.files.ReadableFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceFile implements ReadableFile {

	private static final String FILE_SEPARATOR = "/";
	private static final String BASE_PATH = "";

	private String path;
	private final String name;

	public ResourceFile(String path) {
		this.path = path;
		String[] dirs = path.split(FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}

	public ResourceFile(String... paths) {
		this.path = BASE_PATH;
		for (final String part : paths) {
			this.path = this.path + FILE_SEPARATOR + part;
		}
		final String[] dirs = this.path.split(FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}

	public ResourceFile(ResourceFile file, String subFile) {
		this.path = file.path + FILE_SEPARATOR + subFile;
		this.name = subFile;
	}

	public ResourceFile(ResourceFile file, String... subFiles) {
		this.path = file.path;
		for (String part : subFiles) {
			this.path += (FILE_SEPARATOR + part);
		}
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
		return Class.class.getResourceAsStream(path);
	}

	public BufferedReader getReader() {
		try {
			InputStreamReader isr = new InputStreamReader(getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			return reader;
		} catch (Exception e) {
			System.err.println("Couldn't get reader for " + path);
			throw e;
		}
	}

	public String getName() {
		return name;
	}


}
