package otr.mirror.web.stripesext;

import java.util.Locale;
import net.sourceforge.stripes.format.DefaultFormatterFactory;
import net.sourceforge.stripes.format.Formatter;

/**
 *
 * @author Marcus Krassmann
 */
public class OTRFormatterFactory extends DefaultFormatterFactory {

    @Override
    public Formatter<?> getFormatter(Class<?> clazz, Locale locale, String formatType, String formatPattern) {
        if (clazz == String.class) {
            Formatter<String> formatter = new FilenameFormatter();
            formatter.setFormatType(formatType);
            formatter.setFormatPattern(formatPattern);
            formatter.setLocale(locale);
            formatter.init();
            return formatter;
        }
        return super.getFormatter(clazz, locale, formatType, formatPattern);
    }

}
