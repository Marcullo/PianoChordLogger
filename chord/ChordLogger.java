package chord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.DefaultListModel;

public class ChordLogger {
    String logAbsolutePath;
    
    public ChordLogger() {
        logAbsolutePath = "";
    }

    public boolean isSelectedPathWithFile() {
        File file = new File(logAbsolutePath);
        return file.exists();
    }
    
    public String getSelectedAbsolutePath() {
        return logAbsolutePath;
    }
    
    public void setLogAbsolutePath(String logAbsolutePath) {
        this.logAbsolutePath = logAbsolutePath;
    }
    
    public void save(DefaultListModel<String> chordList, String chordListName) {
        try {
            PrintWriter logPrinter = new PrintWriter(logAbsolutePath);
            logPrinter.println(chordListName);
            for (int i = 0; i < chordList.getSize(); i++) {
                if ((i % 10) == 0) {
                    logPrinter.println();
                }
                logPrinter.printf("%s\t", chordList.get(i));
            }
            logPrinter.println();
            logPrinter.println();
            logPrinter.close();
        } catch (FileNotFoundException ex) {
        }
    }
}
