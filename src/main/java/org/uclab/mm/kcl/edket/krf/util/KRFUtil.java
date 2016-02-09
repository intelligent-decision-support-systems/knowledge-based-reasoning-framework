package org.uclab.mm.kcl.edket.krf.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class KRFUtil {
    private static Logger log = LogManager.getLogger(KRFUtil.class);
    public static ObjectMapper objectMapper = new ObjectMapper();
    
    public static int timeToSec(String time) {
        time = time.replaceAll(" ", "").toLowerCase();
        if (time.length() < 2) {
            log.error("Invalid Time Format: {}", time);
            return 0;
        }
        String[] timeParts = time.split(":");
        int seconds = 0;
        for (String part : timeParts) {
            if (part == null || part.length() == 1) {
                continue;
            }
            seconds += getSeconds(part);
        }
        return seconds;
    }

    public static int getSeconds(String timePart) {
        int secs = 0;
        if (timePart.contains("h")) {
            timePart = timePart.replace("h", "");
            if (timePart.length() < 1) {
                log.error("Invalid hours: {}", timePart);
                return 0;
            }
            int hour = Integer.parseInt(timePart);
            secs = hour * 3600;
        } else if (timePart.contains("m")) {
            timePart = timePart.replace("m", "");
            if (timePart.length() < 1) {
                log.error("Invalid minutes: {}", timePart);
                return 0;
            }
            int minute = Integer.parseInt(timePart);
            secs = minute * 60;
        } else {
            if (timePart.contains("s")) {
                timePart = timePart.replace("s", "");
                if (timePart.length() < 1) {
                    log.error("Invalid seconds: {}", timePart);
                    return 0;
                }
                secs = Integer.parseInt(timePart);
            }
        }

        return secs;
    }
}
