package otr.mirror.web.stripesext;

import java.util.Locale;
import net.sourceforge.stripes.format.Formatter;

/**
 *
 * @author Marcus Krassmann
 */
public class FilenameFormatter implements Formatter<String> {

    private String formatType;
    
    @Override
    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Override
    public void setFormatPattern(String pattern) {
        // no pattern supported
    }

    @Override
    public void setLocale(Locale locale) {
        // no locale, just fixed string length
    }

    @Override
    public void init() {
    }

    @Override
    public String format(String filename) {
        if (formatType == null) {
            return filename;
        }
        if (filename == null) {
            return "---";
        }
        
        int digits = Integer.parseInt(formatType);
        if (digits >= filename.length()) {
            return filename;
        } else {
            return filename.substring(0, digits-3) + "...";
        }
    }

}
