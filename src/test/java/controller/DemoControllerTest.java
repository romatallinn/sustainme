package controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
//import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import view.implementation.terminal.TerminalDemoView;
import view.interfaces.IDemoView;

public class DemoControllerTest {

    private DemoController controller;

//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void init() {

        IDemoView view = new TerminalDemoView();
        controller = new DemoController(view);

    }

//    @Test
//    public void appShutdownTest() {
//        exit.expectSystemExitWithStatus(0);
//        controller.applicationShutdown();
//    }


}
