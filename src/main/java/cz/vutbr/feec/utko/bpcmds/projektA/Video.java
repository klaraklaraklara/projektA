package cz.vutbr.feec.utko.bpcmds.projektA;

import java.io.File;

public class Video {

    File file = null;
    String file_name = null;

    public Video(File file, String file_name){
        this.file = file;
        this.file_name = file_name;
    }

    public Video(){};

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
