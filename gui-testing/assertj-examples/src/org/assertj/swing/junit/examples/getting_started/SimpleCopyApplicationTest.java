package org.assertj.swing.junit.examples.getting_started;

import static org.junit.Assert.*;

import org.assertj.swing.aut.getting_started.SimpleCopyApplication;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleCopyApplicationTest {
  private FrameFixture window;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @Before
  public void setUp() {
    SimpleCopyApplication frame = GuiActionRunner.execute(new GuiQuery<SimpleCopyApplication>() {
      protected SimpleCopyApplication executeInEDT() {
        return new SimpleCopyApplication();
      }
    });
    window = new FrameFixture(frame);
    window.show(); // shows the frame to test
  }

  @Test
  public void shouldCopyTextInLabelWhenClickingButton() {
    window.textBox("textToCopy").enterText("Some random text");
    window.button("copyButton").click();
    window.label("copiedText").requireText("Some random text");
  }
  
  @Test
  public void shouldShowEmptyStringAndBeVisible() {
	  window.textBox("textToCopy").requireText("");
	  //Don't use requireVisible - it's broken in the latest build!
	  // window.label("copiedText").requireVisible();
	  //Use "target()" to get the source component + default JUnit assertions
	  assertTrue(window.label("copiedText").target().isVisible());
	  window.button("copyButton").click();
	  window.label("copiedText").requireText("");
  }

  @After
  public void tearDown() {
    window.cleanUp();
  }
}
