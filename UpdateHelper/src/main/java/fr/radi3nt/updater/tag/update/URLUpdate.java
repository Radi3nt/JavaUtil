package fr.radi3nt.updater.tag.update;

import java.net.MalformedURLException;
import java.net.URL;

public interface URLUpdate extends JavaUpdate {

    URL createURL() throws MalformedURLException;

}
