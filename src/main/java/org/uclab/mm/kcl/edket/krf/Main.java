package org.uclab.mm.kcl.edket.krf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.mm.kcl.edket.krf.ui.ReasonerGui;

public class Main {
    private static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Initializing Reasoner...");
        ReasonerGui.invokeGui(args);
    }

}
