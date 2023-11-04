package structures.auxiliars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileContent implements Comparable<FileContent> {
    private String name;
    private List<String> content = new ArrayList<>();

    public FileContent() {}

    public FileContent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContent() {
        return Collections.unmodifiableList(content);
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public void addContent(String content) {
        this.content.add(content);
    }

    @Override
    public String toString() {
        return "FileContent{" +
                "name='" + name + '\'' +
                ", content=" + content.toString() +
                '}';
    }

    @Override
    public int compareTo(FileContent fileContent) {
        return this.name.compareTo(fileContent.getName());
    }
}
